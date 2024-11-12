package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        Pose2d redSubmersible = new Pose2d(0,-35, Math.toRadians(90));
        Pose2d redSample1 = new Pose2d(48,-30,Math.toRadians(90));
        Pose2d redSample2 = new Pose2d(58,-30,Math.toRadians(90));
        Pose2d redSample3 = new Pose2d(68,-30,Math.toRadians(90));
        Pose2d redHumanPlayer = new Pose2d(49,52,Math.toRadians(180));
        Pose2d redBasket = new Pose2d(-52,-52,Math.toRadians(50));
        Pose2d redBasketReturn = new Pose2d(55,0,Math.toRadians(130));
        Pose2d redCollectBackUp = new Pose2d(35,0, Math.toRadians(180));
        Pose2d redHang = new Pose2d(0,-27,Math.toRadians(270));

        Pose2d blueSubmersible = new Pose2d(0,35, Math.toRadians(90));
        Pose2d blueSample1 = new Pose2d(-28,-46,Math.toRadians( 90));
        Pose2d blueSample2 = new Pose2d(-58, -26,Math.toRadians(90));
        Pose2d blueSample3 = new Pose2d(-28, -26,Math.toRadians(90));
        Pose2d blueHumanPlayer = new Pose2d(-49,-52,Math.toRadians(360));
        Pose2d blueBasket = new Pose2d(53,53,Math.toRadians(220));
        Pose2d blueBasketReturn = new Pose2d(-55,0,Math.toRadians(130));
        Pose2d blueCollectBackUp = new Pose2d(-35,0, Math.toRadians(0));
        Pose2d blueHang = new Pose2d(0,27,Math.toRadians(270));

        Pose2d redNeutralSample1Approach = new Pose2d(-57,-24,Math.toRadians(180));
        Pose2d redNeutralSample1 = new Pose2d(-60,-24,Math.toRadians(180));
        Pose2d redNeutral2Approach = new Pose2d(-58,-34,Math.toRadians(90));
        Pose2d redNeutralSample2 = new Pose2d(-58, -30, Math.toRadians(90));
        Pose2d redNeutralSample3 = new Pose2d(-48,-30,Math.toRadians(90));

        Pose2d blueNeutralSample1 = new Pose2d(48,33,Math.toRadians(-90));
        Pose2d blueNeutralSample2Approach = new Pose2d(58,39,Math.toRadians(-90));
        Pose2d blueNeutralSample2 = new Pose2d(58,33,Math.toRadians(-90));
        Pose2d blueNeutralSample3Approach = new Pose2d(57,26,Math.toRadians(0));
        Pose2d blueNeutralSample3 = new Pose2d(62,26,Math.toRadians(0));





        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity blueBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35, 58, Math.toRadians(90)))
                        .lineToLinearHeading(blueSubmersible)
                        .lineToLinearHeading(blueNeutralSample1)
                        .lineToLinearHeading(blueBasket)
                        .lineToLinearHeading(blueNeutralSample2Approach)
                        .lineToLinearHeading(blueNeutralSample2)
                        .lineToLinearHeading(blueBasket)
                        .lineToLinearHeading(blueNeutralSample3Approach)
                        .lineToLinearHeading(blueNeutralSample3)
                        .lineToLinearHeading(blueBasket)
                        .build());
        RoadRunnerBotEntity blueBot1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35, 58, Math.toRadians(-90)))
                        .lineToLinearHeading(redBasket)
                        .lineToLinearHeading(redNeutralSample3)
                        .lineToLinearHeading(redBasket)
                        .lineToLinearHeading(redNeutral2Approach)
                        .lineToLinearHeading(redNeutralSample2)
                        .lineToLinearHeading(redNeutralSample3)

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(blueBot)
                .start();

    }
}