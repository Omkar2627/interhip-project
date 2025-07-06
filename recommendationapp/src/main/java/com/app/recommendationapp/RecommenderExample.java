package com.app.recommendationapp;
import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class RecommenderExample {
    public static void main(String[] args) {
        try {
            // Load data model from CSV
            DataModel model = new FileDataModel(new File("data.csv"));

            // Define user similarity based on Pearson correlation
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Define user neighborhood (nearest 2 users)
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

            // Build recommender
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Recommend items for userID=3
            List<RecommendedItem> recommendations = recommender.recommend(3, 3);

            // Display recommendations
            System.out.println("Recommendations for User 3:");
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Item ID: " + recommendation.getItemID() + ", Estimated Preference: " + recommendation.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
