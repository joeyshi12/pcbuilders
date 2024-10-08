package controllers;

import database.PcComponentDatabase;
import io.javalin.http.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PcComponentController {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final PcComponentDatabase pcComponentDatabase;

    public PcComponentController(PcComponentDatabase pcComponentDatabase) {
        this.pcComponentDatabase = pcComponentDatabase;
    }

    public void getAllCpuComponents(Context ctx) {
        try {
            String idsValue = ctx.queryParam("ids");
            String[] idList = null;
            if (idsValue != null) {
                idList =  idsValue.split(",");
            }
            ctx.json(ProtoUtil.protoListToJsonString(pcComponentDatabase.getCpuComponents(idList)));
        } catch (Throwable e) {
            logger.error("Failed to retrieve all cpu components", e);
            ctx.status(500);
        }
    }

    public void getAllMotherboardComponents(Context ctx) {
        try {
            String idsValue = ctx.queryParam("ids");
            String[] idList = null;
            if (idsValue != null) {
                idList =  idsValue.split(",");
            }
            ctx.json(ProtoUtil.protoListToJsonString(pcComponentDatabase.getMotherboardComponents(idList)));
        } catch (Throwable e) {
            logger.error("Failed to retrieve all motherboard components", e);
            ctx.status(500);
        }
    }

    public void getAllMemoryComponents(Context ctx) {
        try {
            String idsValue = ctx.queryParam("ids");
            String[] idList = null;
            if (idsValue != null) {
                idList =  idsValue.split(",");
            }
            ctx.json(ProtoUtil.protoListToJsonString(pcComponentDatabase.getMemoryComponents(idList)));
        } catch (Throwable e) {
            logger.error("Failed to retrieve all memory components", e);
            ctx.status(500);
        }
    }

    public void getAllStorageComponents(Context ctx) {
        try {
            String idsValue = ctx.queryParam("ids");
            String[] idList = null;
            if (idsValue != null) {
                idList =  idsValue.split(",");
            }
            ctx.json(ProtoUtil.protoListToJsonString(pcComponentDatabase.getStorageComponents(idList)));
        } catch (Throwable e) {
            logger.error("Failed to retrieve all storage components", e);
            ctx.status(500);
        }
    }

    public void getAllVideoCardComponents(Context ctx) {
        try {
            String idsValue = ctx.queryParam("ids");
            String[] idList = null;
            if (idsValue != null) {
                idList =  idsValue.split(",");
            }
            ctx.json(ProtoUtil.protoListToJsonString(pcComponentDatabase.getVideoCardComponents(idList)));
        } catch (Throwable e) {
            logger.error("Failed to retrieve all videoCard components", e);
            ctx.status(500);
        }
    }

    public void getAllPowerSupplyComponents(Context ctx) {
        try {
            String idsValue = ctx.queryParam("ids");
            String[] idList = null;
            if (idsValue != null) {
                idList =  idsValue.split(",");
            }
            ctx.json(ProtoUtil.protoListToJsonString(pcComponentDatabase.getPowerSupplyComponents(idList)));
        } catch (Throwable e) {
            logger.error("Failed to retrieve all powerSupply components", e);
            ctx.status(500);
        }
    }
}
