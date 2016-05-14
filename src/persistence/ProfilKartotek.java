package persistence;

import java.sql.SQLException;

import domain.Profil;

public interface ProfilKartotek {

	public Profil anmodOmProfil(long kundeNummer) throws SQLException;
	
	public void redigerProfil(String navn, String email, Long tlf, Long kundenummer) throws SQLException;
}
