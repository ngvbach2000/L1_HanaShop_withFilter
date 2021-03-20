/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.userhistory;

import bachnv.user.UserDTO;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ngvba
 */
public class UserHistoryDTO implements Serializable{
    private String historyID;
    private UserDTO user;
    private String content;
    private Date date;

    public UserHistoryDTO(String historyID, UserDTO user, String content, Date date) {
        this.historyID = historyID;
        this.user = user;
        this.content = content;
        this.date = date;
    }

    public String getHistoryID() {
        return historyID;
    }

    public void setHistoryID(String historyID) {
        this.historyID = historyID;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
