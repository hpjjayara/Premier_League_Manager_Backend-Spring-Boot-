package com.iit.oop.backend.premierleague.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.ArrayList;

public class HackedObjectInputStream extends ObjectInputStream {

    public HackedObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        ObjectStreamClass resultClassDescriptor = super.readClassDescriptor();

        if (resultClassDescriptor.getName().equals("Match"))
            resultClassDescriptor = ObjectStreamClass.lookup(Match.class);

        if (resultClassDescriptor.getName().equals("Date"))
            resultClassDescriptor = ObjectStreamClass.lookup(Date.class);

        if (resultClassDescriptor.getName().equals("SportsClub"))
            resultClassDescriptor = ObjectStreamClass.lookup(SportsClub.class);

        if (resultClassDescriptor.getName().equals("SchoolFootballClub"))
            resultClassDescriptor = ObjectStreamClass.lookup(SchoolFootballClub.class);

        if (resultClassDescriptor.getName().equals("FootballClub"))
            resultClassDescriptor = ObjectStreamClass.lookup(FootballClub.class);

        if (resultClassDescriptor.getName().equals("UniversityFootballClub"))
            resultClassDescriptor = ObjectStreamClass.lookup(UniversityFootballClub.class);


        return resultClassDescriptor;
    }
}