package edu.sdccd.cisc191.template;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppFrame {
    public static void main (String[] args){
        JFileChooser j = new JFileChooser();
        j.setCurrentDirectory(new File(System.getProperty("user.home")));
        j.showOpenDialog(null);
        File edgeListFile= j.getSelectedFile();
        File edgeListWorkingDir = j.getCurrentDirectory();
        ArrayList<String[]> edgeListLines= new ArrayList<String[]>();
        //regex shit
        //testpattern for lines to be (a,b)
        final String testRegex= "^\\((.*)\\,(.*)\\)$";
        Pattern r= Pattern.compile(testRegex);
        Matcher m;


        try {
            FileReader fileReader = new FileReader(edgeListFile);
            BufferedReader edgeListIn = new BufferedReader(fileReader);
            String line=edgeListIn.readLine();
            while (line != null) {
                m=r.matcher(line);
                if(m.find()){
                    String[] edgeDouble= new String[2];
                    edgeDouble[0]=m.group(1);
                    edgeDouble[1]=m.group(2);
                    edgeListLines.add(edgeDouble);
                }
                line=edgeListIn.readLine();
            }
            edgeListIn.close();
            String[][] edgeListLinesArray= new String[edgeListLines.size()][2];
            for(int i=0; i< edgeListLines.size(); ++i){
                edgeListLinesArray[i][0]=edgeListLines.get(i)[0];
                edgeListLinesArray[i][1]=edgeListLines.get(i)[1];
            }
            String graphJson= EdgesToGraphJson.edgeListToGraph(edgeListLinesArray);
            String fileName= edgeListFile.getName()+"_graphJson.txt";
            PrintWriter jsonToTxt= new PrintWriter(new File(edgeListWorkingDir,fileName));
            jsonToTxt.write(graphJson);
            jsonToTxt.flush();
            jsonToTxt.close();


        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        }
        catch(IOException io){
            System.out.println(io.getMessage());
        }
    }

}