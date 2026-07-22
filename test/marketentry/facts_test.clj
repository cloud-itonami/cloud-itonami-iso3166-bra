(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest bra-has-spec-basis
  (let [sb (facts/spec-basis "BRA")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/rep-spec-basis "BRA")))
    (is (some? (facts/corporate-number-spec-basis "BRA")))))

(deftest catalog-is-scoped-to-bra
  (testing "no unlabeled foreign-country entries (scaffold-copy contamination regression)"
    (is (= #{"BRA"} (set (keys facts/catalog))))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "BRA")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "BRA" all)))
    (is (not (facts/required-evidence-satisfied? "BRA" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["BRA" "ATL"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))

(deftest procurement-basis-current-vs-repealed
  (testing "current basis (Lei 14.133/2021) is recognized"
    (is (true? (facts/procurement-basis-current?
                "BRA" "Lei nº 14.133, de 1º de Abril de 2021 (Lei de Licitações e Contratos Administrativos)"))))
  (testing "the fully-revoked Lei 8.666/1993 is NOT the current basis"
    (is (false? (facts/procurement-basis-current? "BRA" "Lei nº 8.666, de 21 de Junho de 1993"))))
  (testing "the catalog itself records 8.666/1993 as superseded, not as the live legal-basis"
    (let [sb (facts/spec-basis "BRA")]
      (is (re-find #"14\.133" (:legal-basis sb)))
      (is (not (re-find #"8\.666" (:legal-basis sb))))
      (is (re-find #"8\.666" (:procurement-superseded-basis sb)))
      (is (re-find #"REVOKED" (:procurement-superseded-basis sb)))))
  (testing "an unknown jurisdiction has no basis to compare against"
    (is (nil? (facts/procurement-basis-current? "ZZZ" "anything")))))

(deftest pncp-mandatory-publication-and-compras-gov-br-integration
  (testing "PNCP grounding exists and cites Art. 174 / Decreto 10.764/2021"
    (let [pncp (facts/pncp-spec-basis "BRA")]
      (is (some? pncp))
      (is (re-find #"174" (:pncp-legal-basis pncp)))
      (is (re-find #"10\.764" (:pncp-legal-basis pncp)))))
  (testing "Compras.gov.br is described as INTEGRATED into PNCP, never affirmatively as a competing/alternative portal"
    (let [sb (facts/spec-basis "BRA")]
      (is (re-find #"(?i)integrat" (:national-spec sb)))
      (is (not (re-find #"(?i)\bis\s+(a|an|the)\s+(competing|alternative)\b" (:national-spec sb))))))
  (testing "an unknown jurisdiction has no PNCP basis"
    (is (nil? (facts/pncp-spec-basis "ZZZ")))))

(deftest sicaf-is-active-not-deprecated
  (testing "SICAF grounding exists, names MGI, and is not described as deprecated"
    (let [sicaf (facts/sicaf-spec-basis "BRA")]
      (is (some? sicaf))
      (is (re-find #"MGI" (:sicaf-owner-authority sicaf)))
      (is (re-find #"SICAF" (:sicaf-legal-basis sicaf)))
      (is (not (re-find #"(?i)deprecat" (:sicaf-legal-basis sicaf))))))
  (testing "an unknown jurisdiction has no SICAF basis"
    (is (nil? (facts/sicaf-spec-basis "ZZZ")))))

(deftest cnpj-redesim-drei-business-registration
  (testing "CNPJ/business-registration grounding names REDESIM, Receita Federal, and DREI"
    (let [cn (facts/corporate-number-spec-basis "BRA")]
      (is (some? cn))
      (is (re-find #"Receita Federal" (:corporate-number-owner-authority cn)))
      (is (re-find #"REDESIM" (:corporate-number-owner-authority cn)))
      (is (re-find #"DREI" (:corporate-number-owner-authority cn)))
      (is (re-find #"CNPJ" (:corporate-number-legal-basis cn))))))
