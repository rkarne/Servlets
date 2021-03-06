/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 * Provides an Account Balance and Basic Withdrawal/Deposit Operations
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    
    Account acc = new Account();
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException{
        //getting the value of balence and posting it to the site after parsing it to string
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.getWriter().write(String.valueOf(acc.getBalance()));
     }
     protected void doPost (HttpServletRequest request, HttpServletResponse response){
         //check if a withdraw or deposit is being maid if neither is beting made the account gets closed
         if (request.getParameter("withdraw")!= null ){
        double withdraw = Double.parseDouble(request.getParameter("withdraw"));
         acc.withdraw(withdraw);
         }
         else if (request.getParameter("deposit")!=null){
             double dep = Double.parseDouble(request.getParameter("deposit"));
         acc.deposit(dep);
         }
         else {
             acc.close();
         }
    }
}
