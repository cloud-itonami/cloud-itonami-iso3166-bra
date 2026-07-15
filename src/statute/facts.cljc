(ns statute.facts
  "General-law compliance catalog for Brazil (BRA) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt/-bel's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL lexml.gov.br (Rede LexML, Brazil's
  official federal legislative metadata network) URL -- never
  fabricated. A law not in this table has NO spec-basis, full stop;
  extend `catalog`, do not invent an id/url. planalto.gov.br (usually
  the first-choice citation source for Brazilian federal law) returned
  ECONNRESET to WebFetch on every URL tried; lexml.gov.br (an
  equally-official alternative host) rendered directly instead.")

(def catalog
  "iso3 -> vector of statute entries."
  {"BRA"
   [{:statute/id "bra.lei-das-sociedades-por-acoes-1976"
     :statute/title "Lei nº 6.404, de 15 de Dezembro de 1976 (Lei das Sociedades por Ações)"
     :statute/jurisdiction "BRA"
     :statute/kind :law
     :statute/law-number "Lei nº 6.404/1976"
     :statute/url "https://www.lexml.gov.br/urn/urn:lex:br:federal:lei:1976-12-15;6404"
     :statute/url-provenance :official-lexml-gov-br
     :statute/enacted-date "1976-12-15"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "bra.lgpd-2018"
     :statute/title "Lei nº 13.709, de 14 de Agosto de 2018 (Lei Geral de Proteção de Dados Pessoais, LGPD)"
     :statute/jurisdiction "BRA"
     :statute/kind :law
     :statute/law-number "Lei nº 13.709/2018"
     :statute/url "https://www.lexml.gov.br/urn/urn:lex:br:federal:lei:2018-08-14;13709"
     :statute/url-provenance :official-lexml-gov-br
     :statute/enacted-date "2018-08-14"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "bra.clt-1943"
     :statute/title "Decreto-Lei nº 5.452, de 1º de Maio de 1943 (Consolidação das Leis do Trabalho, CLT)"
     :statute/jurisdiction "BRA"
     :statute/kind :law
     :statute/law-number "Decreto-Lei nº 5.452/1943"
     :statute/url "https://www.lexml.gov.br/urn/urn:lex:br:federal:decreto.lei:1943-05-01;5452"
     :statute/url-provenance :official-lexml-gov-br
     :statute/enacted-date "1943-05-01"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:labor :employment}}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-bra statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "BRA")) " BRA statutes seeded with an "
                 "official lexml.gov.br citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
