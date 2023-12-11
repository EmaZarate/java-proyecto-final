package logic;

import data.*;
import entities.*;

public class Login {
	private DataUser du;
	
	public Login() {
		du=new DataUser();
	}
	
	public User validate(User u) {
		/* para hacer más seguro el manejo de passwords este sería un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		try {
			/*MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(u.getPassword().getBytes(StandardCharsets.UTF_8));
			u.setPassword(hash.toString());*/
			
			return du.getByUser(u);			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return u;
		}
	}
}
