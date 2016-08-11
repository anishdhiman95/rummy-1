import java.util.*;

public class RummyUtils {

    public static Boolean isSetOfNCards(List<Card> cards, int n) {
        Boolean isSetOfNCards = true;
        if (cards.size() == n) {
            int faceValue = cards.get(0).getFaceValue();
            Set<Card.Suit> suitsFound = new HashSet<>();
            for (Card card : cards) {
                if (!(card.isJoker())) {
                    if (card.getFaceValue() != faceValue) {
                        isSetOfNCards = false;
                    }
                    if (suitsFound.contains(card.getSuit())) {
                        isSetOfNCards = false;
                    } else {
                        suitsFound.add(card.getSuit());
                    }
                }
            }
        }
        else {
            isSetOfNCards = false;
        }
        return isSetOfNCards;
    }

    public static Boolean isSequenceOfNCards(List<Card> cards, int n) {
        Boolean isSequenceOfNCards = true;
        Boolean couldStartWithAce = false;
        if (cards.size() == n) {
            sort(cards);
            if (cards.get(cards.size() - 1).getFaceValue() == 14) {
                couldStartWithAce = true;
            }
            int faceValueToLookFor = cards.get(0).getFaceValue();
            Card.Suit suitToLookFor = cards.get(0).getSuit();
            for (Card card : cards) {
                if (!(card.isJoker())) {
                    System.out.println("Required: " + faceValueToLookFor);
                    System.out.println("Found: " + card.getFaceValue());
                    if (couldStartWithAce && (faceValueToLookFor == (n + 1)) && card.getFaceValue() == 14) {
                        System.out.println("Entered");
                        isSequenceOfNCards = true;
                    }
                    else if (card.getFaceValue() != faceValueToLookFor) {
                        isSequenceOfNCards = false;
                        couldStartWithAce = false;
                    }
                    faceValueToLookFor ++;
                    if (card.getSuit() != suitToLookFor) {
                        isSequenceOfNCards = false;
                    }
                }
            }
        }
        else {
            isSequenceOfNCards = false;
        }
        return isSequenceOfNCards;
    }

    public static void sortByFaceValue(List<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getFaceValue() - o2.getFaceValue();
            }
        });
    }
    
    public static void sortBySuit(List<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getSuit().ordinal() - o2.getSuit().ordinal();
            }
        });
    }
}
