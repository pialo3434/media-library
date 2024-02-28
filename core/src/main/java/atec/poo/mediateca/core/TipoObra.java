package atec.poo.mediateca.core;

public enum TipoObra {

    Book {
        @Override
        public String toString() {
            return "Book";
        }
    },
    DVD {
        @Override
        public String toString() {
            return "Dvd";
        }

    }
}

