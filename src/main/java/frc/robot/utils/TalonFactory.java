package frc.robot.utils;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.OpenLoopRampsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.VoltageConfigs;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants;

public class TalonFactory {
    /**
     * Creates a basic TalonFX with basic configurations
     * 
     * @param id        the id of the Falcon on the robot (get from PheonixTuner)
     * @param inversion the inversion of the TalonFX (false to spin forward, true to spin backwards)
     * @return          the generated TalonFX object
     */
    public static TalonFX createTalonFX(int id, boolean inversion) {
        TalonFX talon = new TalonFX(id);
        TalonFXConfiguration config = new TalonFXConfiguration();
        CurrentLimitsConfigs cur_config = new CurrentLimitsConfigs();
        VoltageConfigs voltage_config = new VoltageConfigs().withPeakForwardVoltage(Constants.TalonConstants.kVoltageComp);
        OpenLoopRampsConfigs openLoopRampsConfigs = new OpenLoopRampsConfigs().withVoltageOpenLoopRampPeriod(0.4);
        cur_config.withSupplyCurrentLimit(Constants.TalonConstants.kCurrentLimit);
        cur_config.withSupplyCurrentLimitEnable(true);
        
        
        talon.getConfigurator().apply(
            new TalonFXConfiguration()
                .withCurrentLimits(cur_config).withVoltage(voltage_config));

        //talon.configGetSupplyCurrentLimit(Constants.TalonConstants.kCurrentLimit, Constants.TalonConstants.kTimeoutMs);
        //talon.configOpenloopRamp(0.4, Constants.TalonConstants.kTimeoutMs);
        talon.setInverted(inversion);
       // talon.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedzSensor, Constants.TalonConstants.kPIDIdx, Constants.TalonConstants.kTimeoutMs);
       // talon.configVoltageCompSaturation(Constants.TalonConstants.kVoltageComp, Constants.TalonConstants.kTimeoutMs);
       // talon.enableVoltageCompensation(true);       

        return talon;
    }

        /**
     * Creates a basic TalonFX with basic configurations
     * 
     * @param id        the id of the Falcon on the robot (get from PheonixTuner)
     * @param inversion the inversion of the TalonFX (false to spin forward, true to spin backwards)
     * @return          the generated TalonFX object
     */
    public static TalonFX createTalonFX(int id, boolean inversion, String bus_name) {
        TalonFX talon = new TalonFX(id, bus_name);
        TalonFXConfiguration config = new TalonFXConfiguration();
        CurrentLimitsConfigs cur_config = new CurrentLimitsConfigs();
        VoltageConfigs voltage_config = new VoltageConfigs().withPeakForwardVoltage(Constants.TalonConstants.kVoltageComp);
        OpenLoopRampsConfigs openLoopRampsConfigs = new OpenLoopRampsConfigs().withVoltageOpenLoopRampPeriod(0.4);
        cur_config.withSupplyCurrentLimit(Constants.TalonConstants.kCurrentLimit);
        cur_config.withSupplyCurrentLimitEnable(true);
        
        
        talon.getConfigurator().apply(
            new TalonFXConfiguration()
                .withCurrentLimits(cur_config).withVoltage(voltage_config));
        

        //talon.configGetSupplyCurrentLimit(Constants.TalonConstants.kCurrentLimit, Constants.TalonConstants.kTimeoutMs);
        //talon.configOpenloopRamp(0.4, Constants.TalonConstants.kTimeoutMs);
        talon.setInverted(inversion);      

        return talon;
    }
}