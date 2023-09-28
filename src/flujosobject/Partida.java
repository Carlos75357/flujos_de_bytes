package flujosobject;

import java.io.Serializable;
import java.util.Objects;

public class Partida implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idJudador;
	private String nomJugador;
	private long puntos;
	private double tiempo;	
	
	public Partida(int idJudador, String nomJugador, long puntos, double tiempo) {
		this.idJudador = idJudador;
		this.nomJugador = nomJugador;
		this.puntos = puntos;
		this.tiempo = tiempo;
	}

	public Partida() {		
	}

	public int getIdJudador() {
		return idJudador;
	}

	public void setIdJudador(int idJudador) {
		this.idJudador = idJudador;
	}

	public String getNomJugador() {
		return nomJugador;
	}

	public void setNomJugador(String nomJugador) {
		this.nomJugador = nomJugador;
	}

	public long getPuntos() {
		return puntos;
	}

	public void setPuntos(long puntos) {
		this.puntos = puntos;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(idJudador, nomJugador, puntos, tiempo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		return idJudador == other.idJudador && Objects.equals(nomJugador, other.nomJugador) && puntos == other.puntos
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo);
	}

	@Override
	public String toString() {
		return "Partida [idJudador=" + idJudador + ", nomJugador=" + nomJugador + ", puntos=" + puntos + ", tiempo="
				+ tiempo + "]";
	}
	
}
