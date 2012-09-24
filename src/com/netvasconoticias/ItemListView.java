package com.netvasconoticias;


public class ItemListView {

    private String texto;
    private String noticia;
    private int iconeRid;

    public ItemListView() {
    }

    public ItemListView(String texto, String noticia, int iconeRid) {
        this.texto = texto;
        this.iconeRid = iconeRid;
        this.noticia = noticia;
    }

    public String getNoticia() {
		return noticia;
	}

	public void setNoticia(String noticia) {
		this.noticia = noticia;
	}

	public int getIconeRid() {
        return iconeRid;
    }

    public void setIconeRid(int iconeRid) {
        this.iconeRid = iconeRid;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
