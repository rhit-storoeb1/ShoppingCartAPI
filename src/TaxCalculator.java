public class TaxCalculator {

    //given the two-letter code of the state
    //a customer orders from, calculate the tax
    //percentage to add to the total price
    public static double calculateTax(String state){
        //based off of https://worldpopulationreview.com/state-rankings/sales-tax-by-state
        switch(state) {
            case "AL": return 0.0914;
            case "AK": return 0.0143;
            case "AZ": return 0.0837;
            case "AR": return 0.0943;
            case "CA": return 0.0856;
            case "CO": return 0.0763;
            case "CT": return 0.0635;
            case "DE": return 0.0000;
            case "FL": return 0.0705;
            case "GA": return 0.0729;
            case "HI": return 0.0441;
            case "ID": return 0.0603;
            case "IL": return 0.0874;
            case "IN": return 0.0700;
            case "IA": return 0.0682;
            case "KS": return 0.0867;
            case "KY": return 0.0600;
            case "LA": return 0.0945;
            case "ME": return 0.0550;
            case "MD": return 0.0600;
            case "MA": return 0.0625;
            case "MI": return 0.0600;
            case "MN": return 0.0743;
            case "MS": return 0.0707;
            case "MO": return 0.0813;
            case "MT": return 0.0000;
            case "NE": return 0.0685;
            case "NV": return 0.0814;
            case "NH": return 0.0000;
            case "NJ": return 0.0660;
            case "NM": return 0.0782;
            case "NY": return 0.0849;
            case "NC": return 0.0697;
            case "ND": return 0.0685;
            case "OH": return 0.0717;
            case "OK": return 0.0892;
            case "OR": return 0.0000;
            case "PA": return 0.0634;
            case "RI": return 0.0700;
            case "SC": return 0.0743;
            case "SD": return 0.0640;
            case "TN": return 0.0947;
            case "TX": return 0.0819;
            case "UT": return 0.0694;
            case "VT": return 0.0618;
            case "VA": return 0.0565;
            case "WA": return 0.0917;
            case "WV": return 0.0639;
            case "WI": return 0.0544;
            case "WY": return 0.0536;
        }
        return -1;
    }
}