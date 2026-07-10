(ns marketentry.facts "Brazil market-entry catalog.")
(def catalog
  {"BRA" {:name "Federative Republic of Brazil"
          :owner-authority "Ministério da Gestão / Compras.gov.br / PNCP"
          :legal-basis "Lei de Licitações e Contratos (14.133/2021)"
          :national-spec "Compras.gov.br / PNCP supplier registration + CNPJ"
          :provenance "https://www.gov.br/compras/"
          :required-evidence ["CNPJ record"
                              "Compras.gov.br registration record"
                              "Cadastral regularity (SICAF) record"
                              "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / Compras.gov.br"
          :rep-legal-basis "Brazilian legal entity (CNPJ) typically required for federal procurement participation"
          :rep-provenance "https://www.gov.br/compras/"
          :corporate-number-owner-authority "Receita Federal"
          :corporate-number-legal-basis "CNPJ"
          :corporate-number-provenance "https://www.gov.br/receitafederal/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR"
          :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "JPN" {:name "Japan" :owner-authority "GEPS" :legal-basis "unified qualification"
          :national-spec "GEPS" :provenance "https://www.chotatujoho.go.jp/va/com/ShikakuTop.html"
          :required-evidence ["法人番号確認記録" "全省庁統一資格申請記録" "GEPS 事業者登録記録" "日本居住代理人確認記録"]}
   "MEX" {:name "Mexico" :owner-authority "CompraNet" :legal-basis "LAASSP"
          :national-spec "CompraNet" :provenance "https://upcp-compranet.buengobierno.gob.mx/"
          :required-evidence ["RFC record" "CompraNet registration" "Mercantile registry extract" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
