package org.example;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDTO {

    @JsonProperty("version_group")
    private VersionGroup versionGroup;
    @JsonProperty("types")
    private List<Types> types;
    @JsonProperty("sprites")
    private Sprites sprites;
    @JsonProperty("pokemon")
    private Pokemon pokemon;
    @JsonProperty("order")
    private int order;
    @JsonProperty("names")
    private List<String> names;
    @JsonProperty("name")
    private String name;
    @JsonProperty("is_mega")
    private boolean isMega;
    @JsonProperty("is_default")
    private boolean isDefault;
    @JsonProperty("is_battle_only")
    private boolean isBattleOnly;
    @JsonProperty("id")
    private int id;
    @JsonProperty("form_order")
    private int formOrder;
    @JsonProperty("form_names")
    private List<String> formNames;
    @JsonProperty("form_name")
    private String formName;

    public VersionGroup getVersionGroup() {
        return versionGroup;
    }

    public List<Types> getTypes() {
        return types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public int getOrder() {
        return order;
    }

    public List<String> getNames() {
        return names;
    }

    public String getName() {
        return name;
    }

    public boolean getIsMega() {
        return isMega;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public boolean getIsBattleOnly() {
        return isBattleOnly;
    }

    public int getId() {
        return id;
    }

    public int getFormOrder() {
        return formOrder;
    }

    public List<String> getFormNames() {
        return formNames;
    }

    public String getFormName() {
        return formName;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VersionGroup {
        @JsonProperty("url")
        private String url;
        @JsonProperty("name")
        private String name;

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Types {
        @JsonProperty("type")
        private Type type;
        @JsonProperty("slot")
        private int slot;

        public Type getType() {
            return type;
        }

        public int getSlot() {
            return slot;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Type {
        @JsonProperty("url")
        private String url;
        @JsonProperty("name")
        private String name;

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sprites {
        @JsonProperty("front_shiny")
        private String frontShiny;
        @JsonProperty("front_default")
        private String frontDefault;
        @JsonProperty("back_shiny")
        private String backShiny;
        @JsonProperty("back_default")
        private String backDefault;

        public String getFrontShiny() {
            return frontShiny;
        }

        public String getFrontDefault() {
            return frontDefault;
        }

        public String getBackShiny() {
            return backShiny;
        }

        public String getBackDefault() {
            return backDefault;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pokemon {
        @JsonProperty("url")
        private String url;
        @JsonProperty("name")
        private String name;

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }
    }
}
