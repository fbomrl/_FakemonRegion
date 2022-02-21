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

    public String readerCSV(MultipartFile file){
        BufferedReader br = null;
        String linha = "";
        Fakemon fakemon = new Fakemon();

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

                Optional<Fakemon> fakemonRetornado = fakemonDao.findById(fakemon.getId_general());
                //! = negação; Se o fakemonretornado não está presente ...
                if(!fakemonRetornado.isPresent()){
                    //Gravar fakemonretornado;
                    fakemonDao.save(fakemon);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Ocorreu um erro, tente novamente mais tarde";
        }
        catch (IOException e) {
            e.printStackTrace();
            return "Ocorreu um erro, tente novamente mais tarde";
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Ocorreu um erro, tente novamente mais tarde";
                }
            }
        }

        return "Fakemon cadastrados com sucesso!";
    }


}
