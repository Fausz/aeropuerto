package es.iessevero.aeropuerto.clases;


    public enum Descuentos {
        NINGUNO,
        RESIDENTE_ISLA,
        RES_ANT;

        @Override
        public String toString() {
            return this.name();
        }

    }
