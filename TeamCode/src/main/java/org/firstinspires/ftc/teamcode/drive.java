package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="drive", group="main")
public class drive extends OpMode{



    //region Gamepads
    Gamepad currentGamepad1;
    Gamepad previousGamepad1;

    Gamepad currentGamepad2;
    Gamepad previousGamepad2;
    //endregion

    //region Extendo
    Servo extendoL;
    Servo extendoR;
    //endregion

    //region Intake CRServos
    CRServo intakeL;
    CRServo intakeR;
    //endregion

    //region Intake angle
    Servo intakeAngleL;
    Servo intakeAngleR;
    //endregion

    //region Arm
    Servo armAngleL;
    Servo armAngleR;
    //endregion

    //region Claw
    Servo claw;
    Servo clawAngle;
    //endregion

    //region Arm Slides
    DcMotor leftSlide;
    DcMotor rightSlide;
    //endregion


    //MecanumDrive drive;
    Vector2d position = new Vector2d(0,0);
    Pose2d pose = new Pose2d(position, 90);

    double drivePower =.8;
    double xPower;
    double yPower;
    double headingPower;

    int slideTarget;
    double extendoTarget;

    double highBucketHeight;
    //cameraThing cameraThing;
    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        //drive = new MecanumDrive(hardwareMap,pose);

        currentGamepad1 = new Gamepad();
        previousGamepad1 = new Gamepad();
        currentGamepad2 = new Gamepad();
        previousGamepad2 = new Gamepad();

        //cameraThing = new cameraThing(hardwareMap);
        //cameraThing.initCam();
        extendoL = hardwareMap.get(Servo.class, "extendoL");
        extendoR = hardwareMap.get(Servo.class, "extendoR");
        intakeL = hardwareMap.get(CRServo.class, "intakeL");
        intakeR = hardwareMap.get(CRServo.class, "intakeR");
        intakeAngleL = hardwareMap.get(Servo.class, "intakeAngleL");
        intakeAngleR = hardwareMap.get(Servo.class, "intakeAngleR");

        leftSlide = hardwareMap.get(DcMotorEx.class, "leftSlide");
        rightSlide = hardwareMap.get(DcMotorEx.class, "rightSlide");

        armAngleL = hardwareMap.get(Servo.class, "armAngleL");
        armAngleR = hardwareMap.get(Servo.class, "armAngleR");
        claw = hardwareMap.get(Servo.class, "claw");
        clawAngle = hardwareMap.get(Servo.class, "servoAngle");

        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftSlide.setPower(drivePower);
        rightSlide.setPower(drivePower);

        //extendoL.setPosition(1.0);
        slideTarget = leftSlide.getCurrentPosition();

        extendoR.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void loop() {
        //region drive
        xPower = -currentGamepad1.left_stick_y;
        yPower = -currentGamepad1.left_stick_x;
        headingPower = -currentGamepad1.right_stick_x;

        xPower *= drivePower;
        yPower *= drivePower;
        headingPower *= drivePower;


        if (currentGamepad1.dpad_up) {
            xPower = drivePower;
            yPower = 0;
            headingPower = 0;
        } else if (currentGamepad1.dpad_down) {
            xPower = -drivePower;
            yPower = 0;
            headingPower = 0;
        } else if (currentGamepad1.dpad_left) {
            xPower = 0;
            yPower = drivePower;
            headingPower = 0;
        } else if (currentGamepad1.dpad_right) {
            xPower = 0;
            yPower = -drivePower;
            headingPower = 0;
        }

        if (currentGamepad1.right_bumper){
            drivePower = 1;
        } else if(currentGamepad1.left_bumper){
            drivePower = .4;
        } else{
            drivePower = .8;
        }
        //endregion


        //region slides
        if (currentGamepad2.dpad_up){
            slideTarget = 1950;
        }
        if (currentGamepad2.dpad_down){
            slideTarget = 10;
        }
        leftSlide.setTargetPosition(slideTarget);
        rightSlide.setTargetPosition(slideTarget);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        /*if(currentGamepad2.right_bumper && !previousGamepad2.right_bumper){
            slideTarget +=10;
        }
        if(currentGamepad2.left_bumper && !previousGamepad2.left_bumper){
            slideTarget -=10;
            if (slideTarget <0){
                slideTarget = 0;
            }
        }*/
        //endregion

        //region extendo
        if(currentGamepad2.dpad_left && !previousGamepad2.dpad_left) {
            extendoTarget += .05;
            extendoL.setPosition(extendoTarget);
            extendoR.setPosition(extendoTarget);
            if (extendoTarget > 1.0) {
                extendoTarget = 1.0;
            }
        }
        if(currentGamepad2.dpad_right && !previousGamepad2.dpad_right){
            extendoTarget -=.05;
            extendoL.setPosition(extendoTarget);
            extendoR.setPosition(extendoTarget);
            if(extendoTarget<0.0){
                extendoTarget=0;
            }
        }
        //endregion

        Vector2d gamepadInput = new Vector2d(xPower, yPower);
        PoseVelocity2d poseVelocity2d = new PoseVelocity2d(gamepadInput, headingPower);


        //drive.setDrivePowers(poseVelocity2d);
        /*telemetry.addData("xPower", xPower);
        telemetry.addData("yPower", yPower);
        telemetry.addData("headingPower", headingPower);
        telemetry.addData("leftStick x", currentGamepad1.left_stick_x);
        telemetry.addData("lefStick y", currentGamepad1.left_stick_y);
        telemetry.addData("rightStick x", currentGamepad1.right_stick_x);*/
        telemetry.addData("extendo target", extendoTarget);
        telemetry.addData("extendoL servo pos", extendoL.getPosition());
        telemetry.addData("extendoR servo pos", extendoR.getPosition());
        telemetry.addData("leftSlide Target", leftSlide.getTargetPosition());
        telemetry.addData("leftSlide", leftSlide.getCurrentPosition());
        telemetry.addData("rightSLide Target", rightSlide.getTargetPosition());
        telemetry.addData("rightSlide", rightSlide.getCurrentPosition());

        previousGamepad1.copy(currentGamepad1);
        previousGamepad2.copy(currentGamepad2);

        currentGamepad1.copy(gamepad1);
        currentGamepad2.copy(gamepad2);
        }
    }