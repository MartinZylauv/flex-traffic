package persistence;

import java.sql.SQLException;

import domain.Profil;

public interface ProfilKartotek {

	public Profil anmodOmProfil(double kundeNummer) throws SQLException;
}
