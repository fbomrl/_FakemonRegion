package com.example.metanik.service;

import com.example.metanik.dao.FakemonDao;
import com.example.metanik.model.Fakemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;

@Service
public class FakemonService {

    @Autowired
    private FakemonDao fakemonDao;

    public String readerCSV(MultipartFile file) {
        BufferedReader br = null;
        String linha = "";
        Fakemon fakemon = new Fakemon();
        int contador=0;

        try {
            InputStream is =  file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            br.readLine();
            while ((linha = br.readLine()) != null) {

                String[] celulas = linha.split(";");

                fakemon.setId_general(Integer.valueOf(celulas[0]));
                fakemon.setId_reg(Integer.valueOf(celulas[1]));
                fakemon.setName_fkm(celulas[2]);
                fakemon.setType1(celulas[3]);
                fakemon.setType2(celulas[4]);
                fakemon.setSpecies(celulas[5]);
                fakemon.setAbilities1(celulas[6]);
                fakemon.setAbilities2(celulas[7]);
                fakemon.setHiddenability(celulas[8]);
                fakemon.setHeight(celulas[9]);
//                fakemon.setWeight(celulas[10]);
//                fakemon.setHabitat(celulas[11]);
//                fakemon.setFeeding(celulas[12]);
//                fakemon.setEvoby(celulas[13]);
//                fakemon.setInspiration1(celulas[14]);
//                fakemon.setInspiration2(celulas[15]);



                Optional<Fakemon> fakemonRetornado = fakemonDao.findById(fakemon.getId_general());
                //! = negação; Se o fakemonretornado não está presente ...
                if(!fakemonRetornado.isPresent()){
                    //Gravar fakemonretornado;
                    fakemonDao.save(fakemon);
                    contador +=1;

                }

            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Ocorreu um erro, tente novamente mais tarde";
        }
        catch (IOException e) {
            e.printStackTrace();
            return "Ocorreu um erro, tente novamente mais tarde";
        }catch(Exception e) {
            e.printStackTrace();
            String retorno = null;

            return retorno;
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Ocorreu um erro, tente novamente mais tarde";
                }
            }
        }
            if(contador==0){
                return "Nenhum Fakemon novo cadastrado";
            }
            return contador+" Fakemon Cadastrados com Sucesso!";
    }


}
