(ns culture.facts
  "Country-level regional-culture catalog for Brazil (BRA) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"BRA"
   [{:culture/id "bra.dish.feijoada"
     :culture/name "Feijoada"
     :culture/country "BRA"
     :culture/kind :dish
     :culture/summary "Bean stew with beef or pork prepared across the Portuguese-speaking world; of Portuguese origin, by the 19th century it had become Brazil's national dish."
     :culture/url "https://en.wikipedia.org/wiki/Feijoada"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bra.dish.pao-de-queijo"
     :culture/name "Pão de queijo"
     :culture/country "BRA"
     :culture/kind :dish
     :culture/summary "Small baked cheese roll made with cassava flour, a popular snack and breakfast food in Brazil; a traditional Brazilian recipe originating in the state of Minas Gerais."
     :culture/url "https://en.wikipedia.org/wiki/P%C3%A3o_de_queijo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bra.dish.moqueca"
     :culture/name "Moqueca"
     :culture/country "BRA"
     :culture/kind :dish
     :culture/summary "Brazilian seafood stew of shrimp or fish with tomatoes, onions, garlic, lime and coriander; regional variations across Brazilian states, notably Bahia and Espírito Santo, compete for the position of national culinary symbol."
     :culture/url "https://en.wikipedia.org/wiki/Moqueca"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bra.dish.acaraje"
     :culture/name "Acarajé"
     :culture/country "BRA"
     :culture/kind :dish
     :culture/summary "Deep-fried black-eyed-pea fritter brought to Brazil by enslaved Yoruba people; traditionally encountered in the northeastern state of Bahia, especially Salvador, and listed as national intangible historic heritage in 2004."
     :culture/url "https://en.wikipedia.org/wiki/Akara"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bra.dish.brigadeiro"
     :culture/name "Brigadeiro"
     :culture/country "BRA"
     :culture/kind :dish
     :culture/summary "Traditional Brazilian chocolate confection of condensed milk, cocoa powder and butter, created in 1945 and popular throughout the country, especially at festive events."
     :culture/url "https://en.wikipedia.org/wiki/Brigadeiro"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bra.beverage.caipirinha"
     :culture/name "Caipirinha"
     :culture/country "BRA"
     :culture/kind :beverage
     :culture/summary "Brazilian cocktail made with cachaça, sugar, lime and ice; declared Brazilian Cultural Heritage in 2003 and an official IBA cocktail."
     :culture/url "https://en.wikipedia.org/wiki/Caipirinha"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bra.beverage.cachaca"
     :culture/name "Cachaça"
     :culture/country "BRA"
     :culture/kind :beverage
     :culture/summary "Distilled spirit made from fermented sugarcane juice, produced in Brazil since the 16th century; the most popular spirit in Brazil and base of the caipirinha."
     :culture/url "https://en.wikipedia.org/wiki/Cacha%C3%A7a"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bra.festival.rio-carnival"
     :culture/name "Carnival in Rio de Janeiro"
     :culture/name-local "Carnaval do Rio de Janeiro"
     :culture/country "BRA"
     :culture/kind :festival
     :culture/summary "Festival held every year before Lent in Rio de Janeiro, first held in 1723; considered the biggest celebration of Carnival in the world, with two million people per day on the streets."
     :culture/url "https://en.wikipedia.org/wiki/Carnival_in_Rio_de_Janeiro"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bra.heritage.ouro-preto"
     :culture/name "Ouro Preto"
     :culture/country "BRA"
     :culture/kind :heritage
     :culture/summary "Former colonial mining town in Minas Gerais with well-preserved Baroque architecture from the 18th-century gold rush; designated a UNESCO World Heritage Site in 1980."
     :culture/url "https://en.wikipedia.org/wiki/Ouro_Preto"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-iso3166-bra culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "BRA"))
                 " BRA entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
