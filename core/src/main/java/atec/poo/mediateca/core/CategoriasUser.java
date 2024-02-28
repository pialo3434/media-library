package atec.poo.mediateca.core;

public class CategoriasUser {

    public enum UserStatus {
        ACTIVE {
            @Override
            public String toString() {
                return "Ativo";
            }
        },
        CUMPRIDOR, SUSPENDED {
            @Override
            public String toString() {
                return "Suspenso";
            }
        }
    }

    public enum UserClassification {
        NORMAL {
            @Override
            public String toString() {
                return "Normal";
            }
        },
        FALTOSO {
            @Override
            public String toString() {
                return "Faltoso";
            }
        },
        CUMPRIDOR {
            @Override
            public String toString() {
                return "Cumpridor";
            }
        }
    }

}
