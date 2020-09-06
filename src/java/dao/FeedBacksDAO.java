/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.FeedBacks;
import java.util.List;


public interface FeedBacksDAO {

    public long Count(Integer mode);

    public List<FeedBacks> getAllFeedBacks(Integer currentPage, Integer recordsPerPage);

    public FeedBacks getFeedBackById(Integer id);

    public Boolean insertFeedBack(FeedBacks fb);

    public Boolean updateFeedBack(FeedBacks fb);

    public Boolean deleteFeedBack(Integer id);

    public List<FeedBacks> getFeedBacksFindByName(String feedbackName);

    public List<FeedBacks> getAllStatus1(Integer currentPage, Integer recordsPerPage);

    public List<FeedBacks> getAllStatus2(Integer currentPage, Integer recordsPerPage);
}
