package com.jtc.Model.church.churchEntity;


import com.jtc.Model.church.churchRepo.QueryImplementation;

import javax.swing.*;


public class Login {


        private String nameId = "";
        private String dateString = "";
        private QueryImplementation churchService;

        private boolean logMember = false;
        public Login(String memNum, String datesLogin) {

            churchService = new QueryImplementation();
            nameId = memNum;
            dateString = datesLogin;
        }

        public boolean logName()  {
               if (churchService.findByIdValue(nameId)){
                if (churchService.getCurrentDate(nameId).equalsIgnoreCase(dateString))
                {
                        JOptionPane.showMessageDialog(null, "You have Logged In Before");
                    }
                  //  logMember = true;
                 else// if (!churchService.getCurrentDate(nameId).equalsIgnoreCase(dateString))
                {
                    churchService.updateCurrentDateInfo(nameId, dateString);
                    churchService.updateAttendanceOfMembers(nameId);
                    churchService.updateAttendance(nameId);
                    }
                    logMember = true;
                }


            return logMember;
            }
        }
    //}




