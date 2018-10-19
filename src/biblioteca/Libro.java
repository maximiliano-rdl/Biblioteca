package biblioteca;

public class Libro implements Comparable<Libro>{

	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private int edicion;
	private int anioPub;
	
	
	public void setISBN(String isbn) {
		this.ISBN = isbn;
		
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
		
	}

	public void setAutor(String autor) {
		this.autor = autor;
		
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
		
	}

	public void setEdicion(int edicion) {
		this.edicion = edicion;
		
	}

	public void setAnno_de_publicacion(int anio) {
		this.anioPub = anio;
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (ISBN == null) {
			if (other.ISBN != null)
				return false;
		} else if (!ISBN.equals(other.ISBN))
			return false;
		return true;
	}

	@Override
	public int compareTo(Libro libro) {
		return this.ISBN.compareTo(libro.ISBN);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ISBN + "\t" + titulo + "\t" + autor + "\t" + editorial + "\t" + edicion + "\t" + anioPub;
	}
	
	public String toStringLista() {
		return "ISBN: " + ISBN + "\n" + "TITULO: " + titulo + "\n" + "AUTOR: " + autor + "\n" + "EDITORIAL: " + editorial + "\n" + "EDICION: " + edicion + "\n" + "ANIO: " + anioPub;
	}

}
