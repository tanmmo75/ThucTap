/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Producers;
import java.util.List;

public interface ProducersDAO {

    public long Count();

    public long CountFind(String name);

    public List<Producers> getAllProducers(Integer currentPage, Integer recordsPerPage);

    public Producers getProducerById(Integer id);

    public Boolean insertProducer(Producers pro);

    public Boolean updateProducer(Producers pro);

    public Boolean deleteProducer(Integer id);

    public List<Producers> getAllProducersDropDownList();

    public List<Producers> getProducersFindByName(String name, Integer currentPage, Integer recordsPerPage);
}
