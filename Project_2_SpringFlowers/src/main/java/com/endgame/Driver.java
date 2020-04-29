package com.endgame;

import com.endgame.dao.*;

public class Driver {
	
	private static UserDao ud = new UserDao();
	
	public static void main(String[] args) {
		//ud.generatePassword();
		System.out.println(ud.findByEmail("kamal@gmail.com"));
		
		//System.out.println(ud.updatePass("kamal@gmail.com"));
	}

}
