package br.com.farmacia.dto.enums;

public enum Ativo {
	NAO(0,"NAO"), SIM(1,"SIM");
	
	private int cod;
	private String desc;
	
	Ativo(int cod,String desc){
		this.cod=cod;
		this.desc=desc;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDesc() {
		return desc;
	}
}
