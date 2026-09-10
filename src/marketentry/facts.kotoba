(ns marketentry.facts
  "Brazil (BRA) market-entry / public-procurement compliance catalog.

  Scoped to BRA only. Prior revisions of this catalog carried unlabeled
  USA/JPN/MEX entries byte-identical to entries in unrelated sibling
  repos (scaffold-copy contamination, not deliberate design -- this
  repo's own README/docs/ADR never referenced any jurisdiction other
  than BRA). Removed 2026-07-23; extend `catalog` with a new
  jurisdiction only behind its own primary-source citation, never by
  re-copying another repo's entry verbatim.

  Grounding for the BRA entry below:

  - **Procurement law**: Lei nº 14.133, de 1º de Abril de 2021 (Lei de
    Licitações e Contratos Administrativos) is the CURRENT operative
    framework. It was published 2021-04-01 with a 2-year optional-
    transition window during which the prior Lei nº 8.666/1993 could
    still be used; Lei nº 8.666/1993 was FULLY REVOKED on 2023-12-30.
    As of today, all Union/state/municipal procurement must follow Lei
    14.133/2021 -- a citation of Lei 8.666/1993 as the current basis is
    a citation of repealed law.
  - **PNCP** (Portal Nacional de Contratações Públicas), created by
    Art. 174 da Lei nº 14.133/2021 and regulated by Decreto Federal
    nº 10.764/2021, is the official, centralized, MANDATORY-publication
    repository for all Union/state/municipal procurement. Compras.gov.br
    is one of the \"Sistemas Federais de Logística Pública\" INTEGRATED
    INTO PNCP -- a federal operational system that feeds/is-integrated-
    with PNCP, not a competing/alternative portal. Do not describe them
    as alternatives.
  - **Owning ministry**: Ministério da Gestão e da Inovação em Serviços
    Públicos (MGI) -- the current (est. 2023) full ministry name housing
    Compras.gov.br / central procurement.
  - **SICAF** (Sistema de Cadastramento Unificado de Fornecedores) is
    still active/current, maintained by MGI, mandatory unified supplier
    registration integrated into Compras.gov.br. Not deprecated.
  - **CNPJ / business registration** flows through REDESIM (Lei nº
    11.598/2007) connecting Receita Federal, the Juntas Comerciais
    (state commercial registries, centrally supervised by DREI under
    Lei nº 8.934/1994), and municipal governments. This catalog states
    the REDESIM/Receita-Federal/Juntas-Comerciais/DREI structure only --
    it does NOT assert a specific automaticity claim about a reported
    post-2025-12-01 \"MAT\" module step under Lei Complementar 214/2025
    (VAT/consumption-tax reform), since that claim is unverified
    (lower-confidence, secondary-sourced only as of this catalog
    revision) and needs a primary-source recheck before it can be
    stated as settled fact.")

(def catalog
  {"BRA" {:name "Federative Republic of Brazil"
          :owner-authority "Ministério da Gestão e da Inovação em Serviços Públicos (MGI) -- Compras.gov.br / PNCP"
          :legal-basis "Lei nº 14.133, de 1º de Abril de 2021 (Lei de Licitações e Contratos Administrativos)"
          :procurement-legal-basis "Lei nº 14.133, de 1º de Abril de 2021 (Lei de Licitações e Contratos Administrativos)"
          :procurement-superseded-basis "Lei nº 8.666, de 21 de Junho de 1993 -- FULLY REVOKED 2023-12-30 (after a 2-year optional-transition window that began at Lei 14.133/2021's 2021-04-01 publication); no longer a valid citation for Union/state/municipal procurement"
          :national-spec "PNCP (Portal Nacional de Contratações Públicas) -- created by Art. 174 da Lei nº 14.133/2021, regulamentado pelo Decreto Federal nº 10.764/2021; the official, centralized, MANDATORY-publication repository for all Union/state/municipal procurement. Compras.gov.br is one of the Sistemas Federais de Logística Pública INTEGRATED INTO PNCP (a federal operational system that feeds/is-integrated-with PNCP), not a competing/alternative portal."
          :provenance "https://www.gov.br/pncp/ ; https://www.gov.br/compras/ ; https://www.planalto.gov.br/ccivil_03/_ato2019-2022/2021/lei/l14133.htm"
          :required-evidence ["CNPJ record (Receita Federal, via REDESIM)"
                              "PNCP / Compras.gov.br registration record"
                              "SICAF (Sistema de Cadastramento Unificado de Fornecedores) cadastral regularity record"
                              "Authorized-representative record"]
          :pncp-owner-authority "Ministério da Gestão e da Inovação em Serviços Públicos (MGI)"
          :pncp-legal-basis "PNCP criado pelo Art. 174 da Lei nº 14.133/2021; regulamentado pelo Decreto Federal nº 10.764, de 30 de Julho de 2021. Compras.gov.br é um Sistema Federal de Logística Pública INTEGRADO ao PNCP, não um portal alternativo."
          :pncp-provenance "https://www.gov.br/pncp/ ; https://www.planalto.gov.br/ccivil_03/_ato2019-2022/2021/decreto/D10764.htm"
          :sicaf-owner-authority "Ministério da Gestão e da Inovação em Serviços Públicos (MGI)"
          :sicaf-legal-basis "SICAF (Sistema de Cadastramento Unificado de Fornecedores) -- cadastro unificado obrigatório de fornecedores, integrado ao Compras.gov.br; ativo e vigente"
          :sicaf-provenance "https://www.gov.br/compras/"
          :rep-owner-authority "contracting authorities / Compras.gov.br / PNCP"
          :rep-legal-basis "Brazilian legal entity (CNPJ) typically required for federal procurement participation"
          :rep-provenance "https://www.gov.br/compras/"
          :corporate-number-owner-authority "Receita Federal -- via REDESIM (Lei nº 11.598/2007), em articulação com as Juntas Comerciais estaduais (supervisão central do DREI, Lei nº 8.934/1994) e as prefeituras municipais"
          :corporate-number-legal-basis "CNPJ (Cadastro Nacional da Pessoa Jurídica), emitido no fluxo REDESIM"
          :corporate-number-provenance "https://www.gov.br/receitafederal/ ; https://www.gov.br/empresas-e-negocios/pt-br/redesim ; https://www.gov.br/drei/"}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "cloud-itonami-iso3166-bra R0 catalog: BRA only. Extend `marketentry.facts/catalog` behind its own primary-source citation, never fabricate or copy another jurisdiction's entry verbatim."})))
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
(defn pncp-spec-basis
  "The jurisdiction's PNCP (mandatory-publication procurement portal)
  grounding, or nil."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:pncp-owner-authority sb)
      (select-keys sb [:pncp-owner-authority :pncp-legal-basis :pncp-provenance]))))
(defn sicaf-spec-basis
  "The jurisdiction's SICAF (unified supplier registration) grounding,
  or nil."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:sicaf-owner-authority sb)
      (select-keys sb [:sicaf-owner-authority :sicaf-legal-basis :sicaf-provenance]))))
(defn procurement-basis-current?
  "Is `cited-basis` the CURRENT procurement legal basis for `iso3`, as
  opposed to a superseded one (for BRA: the fully-revoked Lei
  nº 8.666/1993)? An engagement citing the repealed law is not citing
  current law."
  [iso3 cited-basis]
  (when-let [sb (spec-basis iso3)]
    (= cited-basis (:procurement-legal-basis sb))))
