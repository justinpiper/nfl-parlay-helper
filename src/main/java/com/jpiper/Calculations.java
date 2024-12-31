package com.jpiper;

public class Calculations {
    //DEFENSIVE CALCULATIONS }----------
    public static double runDefGrade(double rushYdsPerAtt, double leagueAvgYPA) {
        if (leagueAvgYPA != 0) {
            return (rushYdsPerAtt/leagueAvgYPA) * 100;
        } else {
            System.out.println("[WARN] Something went wrong during calculation.");
            return 0;
        }
    } //For Run Defense Grade, lower is better.

    public static double tightEndVulnerability(double tightEndYdsAllowed, double totalYdsAllowed) {
        if (totalYdsAllowed != 0) {
            return tightEndYdsAllowed/totalYdsAllowed;
        } else {
            System.out.println("[WARN] Something went wrong during calculation.");
            return 0;
        }
    }

    public static double deepBallDefense(double ydsAllowedOnDeepPasses, double totalYdsAllowed) {
        if (totalYdsAllowed != 0) {
            return ydsAllowedOnDeepPasses/totalYdsAllowed;
        } else {
            System.out.println("[WARN] Something went wrong during calculation.");
            return 0;
        }
    } //A deep pass must be 20 yards or greater.

    //OFFENSIVE CALCULATIONS }----------
    public static double runEffectiveness(double rushYdsPerCarry, double leagueAvgYPC) {
        if (leagueAvgYPC != 0) {
            return rushYdsPerCarry/leagueAvgYPC;
        } else {
            System.out.println("[WARN] Something went wrong during calculation.");
            return 0;
        }
    }

    public static double passEffectiveness(double passYdsPerAtt, double leagueAvgYPC) {
        if (leagueAvgYPC != 0) {
            return passYdsPerAtt/leagueAvgYPC;
        } else {
            System.out.println("[WARN] Something went wrong during calculation.");
            return 0;
        }
    }

    public static double redZoneSuccess(double touchdownsInRedZone, double redZoneAttempts) {
        if (redZoneAttempts != 0) {
            return touchdownsInRedZone/redZoneAttempts;
        } else {
            System.out.println("[WARN] Something went wrong during calculation.");
            return 0;
        }
    }
}
