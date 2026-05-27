package br.com.alura.screenmatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime");

    private String categoriaOmdb;
    private String categoriaEmPtBr;

    Categoria(String categoriaOmdb, String categoriaEmPtBr){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEmPtBr = categoriaEmPtBr;
    }

    public static Categoria fromString(String text){
        for (Categoria categoria : Categoria.values()){
            if(categoria.categoriaOmdb.equals(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Categoria fromPtBr(String text){
        for (Categoria categoria : Categoria.values()){
            if(categoria.categoriaEmPtBr.equals(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
