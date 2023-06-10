package br.unitins.model;

public enum TipoArma {
    
            PISTOLA (1, "Pistola"),
            ESCOPETAS (2, "Escopetas"),
            SUBMETRALHADORAS (3, "Submetralhadoras"),
            RIFLES (4, "Rifles"),
            RIFLESDEPRECISAO (5, "RP"),
            METRALHADORAS (6, "Metralhadoras"),
            FACAS (7, "Facas");


    private int id;
    private String label;

    TipoArma(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoArma valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for(TipoArma tipoarma : TipoArma.values()) {
            if (id.equals(tipoarma.getId()))
                return tipoarma;
        } 
        throw new IllegalArgumentException("Id inválido:" + id);
    }

}
