package user_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

public class Controller implements ActionListener{
	private UserInterface UI;
    private EventListener eventlistener;
    private ActionListener actionlistener;
    
    public Controller(UserInterface UI){
        this.UI = UI;  
    }
    
//    public void control(){        
//        actionlistener = new ActionListener() {
//              public void actionPerformed(ActionEvent actionEvent) {                  
//                  System.out.println("aaa");
//              }
//        };                
//        UI.getButton().setOnAction((event) -> {
//		    System.out.println("Button Action");
//		}); 
//
//    }
    

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
