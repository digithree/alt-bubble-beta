package com.simonkenny.altbubblebeta;

/**
 * Created by simonkenny on 20/03/15.
 */
public class InteractionData {
    private String name;
    private String description;
    private boolean solved;
    private boolean playing;
    private boolean active;

    public InteractionData(String name, String description, boolean solved, boolean active) {
        this.name = name;
        this.description = description;
        this.solved = solved;
        playing = false;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
