package br.com.lima.api.entities.enums;


public enum FilmCategory {

	ADVENTURE(1),
	COMEDY(2),
	ACTION(3),
	SCIENCEFICTION(4),
	HORROR(5),
	ANIMATION(6);
	
	private int code;
	
	private FilmCategory(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static FilmCategory valueOf(int code) {
		for (FilmCategory value : FilmCategory.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
	
}
