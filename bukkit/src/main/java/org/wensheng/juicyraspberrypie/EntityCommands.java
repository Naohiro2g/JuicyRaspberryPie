package org.wensheng.juicyraspberrypie;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class EntityCommands {
    private RemoteSession session;

    public EntityCommands(RemoteSession session) {
        this.session = session;
    }

    public void handleEntityCommand(String c, String[] args) {
        Entity entity = Bukkit.getEntity(UUID.fromString(args[0]));
        if (entity == null) {
            session.send("Entity not found");
            return;
        }

        switch (c) {
            case "entity.getPos":
                handleEntityGetPos(entity);
                break;
            case "entity.setPos":
                handleEntitySetPos(entity, args);
                break;
            case "entity.getRotation":
                handleEntityGetRotation(entity);
                break;
            case "entity.setRotation":
                handleEntitySetRotation(entity, args);
                break;
            case "entity.getPitch":
                handleEntityGetPitch(entity);
                break;
            case "entity.setPitch":
                handleEntitySetPitch(entity, args);
                break;
            case "entity.getYaw":
                handleEntityGetYaw(entity);
                break;
            case "entity.setYaw":
                handleEntitySetYaw(entity, args);
                break;
            case "entity.remove":
                handleEntityRemove(entity);
                break;
            default:
                session.send("Unknown entity command: " + c);
                break;
        }
    }

    private void handleEntityGetPos(Entity entity) {
        Location loc = entity.getLocation();
        session.send(loc.getX() + "," + loc.getY() + "," + loc.getZ());
    }

    private void handleEntitySetPos(Entity entity, String[] args) {
        Location loc = parseRelativeBlockLocation(args[1], args[2], args[3]);
        entity.teleport(loc);
    }

    private void handleEntityGetRotation(Entity entity) {
        Location loc = entity.getLocation();
        session.send(loc.getYaw() + "," + loc.getPitch());
    }

    private void handleEntitySetRotation(Entity entity, String[] args) {
        Location loc = entity.getLocation();
        loc.setYaw(Float.parseFloat(args[1]));
        loc.setPitch(Float.parseFloat(args[2]));
        entity.teleport(loc);
    }

    private void handleEntityGetPitch(Entity entity) {
        session.send(String.valueOf(entity.getLocation().getPitch()));
    }

    private void handleEntitySetPitch(Entity entity, String[] args) {
        Location loc = entity.getLocation();
        loc.setPitch(Float.parseFloat(args[1]));
        entity.teleport(loc);
    }

    private void handleEntityGetYaw(Entity entity) {
        session.send(String.valueOf(entity.getLocation().getYaw()));
    }

    private void handleEntitySetYaw(Entity entity, String[] args) {
        Location loc = entity.getLocation();
        loc.setYaw(Float.parseFloat(args[1]));
        entity.teleport(loc);
    }

    private void handleEntityRemove(Entity entity) {
        entity.remove();
    }

    private Location parseRelativeBlockLocation(String x, String y, String z) {
        return new Location(null, Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
    }
}