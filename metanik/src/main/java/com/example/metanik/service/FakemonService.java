package com.example.metanik.service;

import com.example.metanik.dao.FakemonDao;
import com.example.metanik.model.FakemonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class FakemonService {

    @Autowired
    private FakemonDao fakemonDao;

    public Iterable listarFakemon(){

        return this.fakemonDao.findAll();
    }
    public FakemonModel filtrarFakemon(int id_general){
        FakemonModel fkm = fakemonDao.findById(id_general).orElse(null);
        return fkm;
    }

    public FakemonModel gravarFakemon(FakemonModel fakemonModel){
        Optional<FakemonModel> fakemonRetornado = fakemonDao.findById(fakemonModel.getId_general());

        if (fakemonRetornado.isPresent()) return null;
        LocalDateTime now = DateTimeFormatter();

        fakemonModel.setCreatedDate(now);
        fakemonModel.setUpdatedDate(now);

        FakemonModel fkm = fakemonDao.save(fakemonModel);

        return fkm;
    }

    public String readerCSV(MultipartFile file) {
        BufferedReader br = null;
        String linha = "";
        FakemonModel fakemonModel = new FakemonModel();
        int contador = 0;

        try {
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            br.readLine();
            while ((linha = br.readLine()) != null) {

                String[] celulas = linha.split(";",-1);

                fakemonModel.setId_general(Integer.valueOf(celulas[0]));
                fakemonModel.setId_reg(Integer.valueOf(celulas[1]));
                fakemonModel.setName_fkm(celulas[2]);
                fakemonModel.setType1(celulas[3]);
                fakemonModel.setType2(celulas[4]);
                fakemonModel.setSpecies(celulas[5]);
                fakemonModel.setAbilities1(celulas[6]);
                fakemonModel.setAbilities2(celulas[7]);
                fakemonModel.setHiddenability(celulas[8]);
                fakemonModel.setHeight(celulas[9]);
                fakemonModel.setWeight(celulas[10]);
                fakemonModel.setHabitat(celulas[11]);
                fakemonModel.setFeeding(celulas[12]);
                fakemonModel.setEvoby(celulas[13]);
                fakemonModel.setInspiration1(celulas[14]);
                fakemonModel.setInspiration2(celulas[15]);

                Optional<FakemonModel> fakemonRetornado = fakemonDao.findById(fakemonModel.getId_general());
                //! = negação; Se o fakemonretornado não está presente ...
                if (!fakemonRetornado.isPresent()) {
                    //Gravar fakemonretornado;
                    fakemonModel.setCreatedDate(LocalDateTime.now());
                    fakemonModel.setUpdatedDate(LocalDateTime.now());
                    fakemonDao.save(fakemonModel);
                    contador += 1;

                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao processar o Fakemon de ID: " + fakemonModel.getId_general());
            e.printStackTrace();
            return "Ocorreu um erro, tente novamente mais tarde";
        } catch (IOException e) {
            System.out.println("Erro ao processar o Fakemon de ID: " + fakemonModel.getId_general());
            e.printStackTrace();
            return "Ocorreu um erro, tente novamente mais tarde";
        } catch (Exception e) {
            System.out.println("Erro ao processar o Fakemon de ID: " + fakemonModel.getId_general());

            e.printStackTrace();
            String retorno = null;
            return retorno;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Erro ao processar o Fakemon de ID: " + fakemonModel.getId_general());
                    e.printStackTrace();
                    return "Ocorreu um erro, tente novamente mais tarde";
                }
            }
        }
        if (contador == 0) {
            return "Nenhum Fakemon novo cadastrado";
        }
        return contador + " Fakemon Cadastrados com Sucesso!";
    }

    public LocalDateTime DateTimeFormatter (){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime now = LocalDateTime.parse(LocalDateTime.now().atZone(ZoneId.of("GMT-3")).
                toLocalDateTime().format(formatter));

        return now;

    }



}
