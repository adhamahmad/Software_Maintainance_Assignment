package courses ;

import java.util.HashMap;
import java.util.Map;

import gateways.EmailGateway;
import gateways.IGateway;
import gateways.SMSGateway;
import messages.Message;
import users.User;
public class CoursePublisher 
{
    Course course;
    HashMap<IGateway, User> subscribers = new HashMap<IGateway, User>();

    public void subscribe(String gateWay, User user){
        IGateway gg;
        if(gateWay.equals("Email")  ){
            gg = new EmailGateway();
            subscribers.put(gg, user);
		}
		else if(gateWay.equals("SMS") ){
            gg = new SMSGateway();
            subscribers.put(gg, user);
		}
		else if(gateWay.equals("Both")){
            gg = new EmailGateway();
            subscribers.put(gg, user);
            gg = new SMSGateway();
            subscribers.put(gg, user);
		}
    }
    public void getCourse(Course course){
        this.course = course;
    }

    public void notify(Message message)
    {   // 3rf no3 el message 
        // hget el user for notification
        for (Map.Entry<IGateway, User> entry : subscribers.entrySet()) {
            Message temp = message;
            entry.getKey().sendMessage(entry.getValue(),temp);

            
        }
    }
}