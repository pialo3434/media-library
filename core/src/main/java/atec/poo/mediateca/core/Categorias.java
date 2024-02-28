package atec.poo.mediateca.core;

public enum Categorias {
    REFERENCIA {
        @Override
        public String toString() {
            return "REFERENCIA";
        }
    },
    FICTION {
        @Override
        public String toString() {
            return "FICTION";
        }
    },
    SCITECH {
        @Override
        public String toString() {
            return "SCITECH";
        }
    },
}
