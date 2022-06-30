import client.TrelloClient;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static client.TrelloClient.*;
import static http.refactored.entities.ServiceInit.init;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;


public class TrelloTests {
   public final String CARD_UNIQUE_ID = "5a27e722e2f04f3ab6924931";
   public final String BOARD_ID = "5a27e3b62fef5d3a74dca48a";

   private ResponseSpecification responseSpec = new ResponseSpecBuilder()
           .expectStatusCode(200)
           .expectContentType(ContentType.JSON)
           .build();

   @BeforeClass
   public void initClass(){
      init(TrelloClient.class);
    }

   @Test
   public void createNewBoardTest(){
      String boardName = "Derda Ertaş Board " + random(12, true, true);
      String body = "{\"name\":\"" + boardName + "\"}";

      boards
              .given
              .body(body)
      .when()
              .post(boards.resource)
      .then()
              .spec(responseSpec);
   }

   @Test
   public void getBoardById() {

      Response response =
               board
               .given
               .pathParam("board_id", BOARD_ID)
     .when()
            .get(board.resource);

     response.then()
            .spec(responseSpec)
            .body("id", equalTo(BOARD_ID));
}

   @Test
   public void getBoardCardsList() {
      getBoardCardsList
              .given
              .pathParam("board_id", BOARD_ID)
       .when()
              .get(getBoardCardsList.resource)
       .then()
              .spec(responseSpec)
              .body("name.size()", equalTo(6));
   }

   @Test
   public void postNewCommentToCard() {
      String newComment = "New comment" + random(7, true, false);
      String body = "{\"text\": \"" + newComment + "\"}";

      postNewCommentToCard
              .given
              .pathParam("card_id",CARD_UNIQUE_ID)
              .body(body)
       .when()
              .post(postNewCommentToCard.resource)
       .then()
              .spec(responseSpec)
              .body("data.text", containsString(newComment));
   }
   @Test
   public void deleteCard() {
      deleteCard
              .given
              .pathParam("board_id", BOARD_ID)
              .when()
              .get(deleteBoards.resource)
              .then()
              .spec(responseSpec);
   }

   @Test
   public void deleteBoard() {
      deleteBoards
              .given
              .pathParam("board_id", BOARD_ID)
              .when()
              .get(deleteBoards.resource)
              .then()
              .spec(responseSpec);
   }

}


