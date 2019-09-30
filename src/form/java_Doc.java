/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

public class java_Doc {
    public static void main(String[] args){
        loadingScreen LS = new loadingScreen();
        LS.setVisible(true);
        try {            
            for(int i = 0; i <=100; i++){
                login lg = new login();
                if(i == 100){
                    LS.dispose();
                    Thread.sleep(2000);
                    lg.setVisible(true);
                }else if(i % 2 == 0){
                    Thread.sleep(1);
                    LS.jLabel3.setVisible(false);
                    LS.jLabel1.setText(Integer.toString(i) + " %");
                }else if(i % 2 == 1){
                    Thread.sleep(1);
                    LS.jLabel3.setVisible(true);
                    LS.jLabel1.setText(Integer.toString(i) + " %");
                }             
            }
        } catch (Exception e) {
        
        }
    }
}
