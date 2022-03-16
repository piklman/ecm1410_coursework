package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


/**
 * BadCyclingPortal is a minimally compiling, but non-functioning implementor
 * of the CyclingPortalInterface interface.
 *
 * @author Diogo Pacheco
 * @version 1.0
 *
 */
public class CyclingPortal implements CyclingPortalInterface {
    // Attributes
    private List<Race> races;
	// private Map<int, int> raceStages;
	private List<Rider> riders;
	private List<Team> teams;

    // Constructor
    public CyclingPortal()
    {
        this.races = new List<Race>();
        this.riders = new List<Rider>();
        this.teams = new List<Team>();
        this.raceStages = new Map<int, int>();
    }

    // Methods
	@Override
	public int[] getRaceIds() {
        // Iteratively adds the ID of each race in the races list to raceIds, then returns raceIds.
        int[] raceIds = new int[races.length];
		for (i=0; i < this.races.length; i++)
        {
            raceIds[i] = this.races[i].getId();
        }
	}

	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
        // Creates a Race object, with necessary checks in place.
		if (name.length <= 30 && name != null && name != "")
		{
            // The name is of correct size, is not null and is not empty.
			for (i=0; i < name.length; i++)
			{
				if (isWhitespace(name[i]))
				{
                    // The name has whitespace; invalid.
					throw new InvalidNameException("No whitespace allowed in race names.");
				}
			}

			for (i=0; i<this.races.length; i++)
			{
				if (this.races[i].getName() == name)
				{
                    // The name has already been taken.
					throw new IllegalNameException("This race name is already taken.");
				}
			}

            // The name is valid; create the Race.
			Race race = new Race(name, description);
		}
		else
		{
            // The name is not of correct size, or is null or empty.
			throw new InvalidNameException("Race name length is of incorrect length (1 to 30 characters) or is null.")
		}
		return -1; // !
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
        // Iteratively searches for the first (and only) Race with the given ID, and removes it.
        for (int i=0; i < races.length; i++)
        {
            if (races[i].getId() == raceId)
            {
                races.remove(i);
            }
        }
	}

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		return this.raceStages[raceId].length;
	}

	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		return this.raceStages[raceId];
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		if (name.length <= 30 && name != null && name != "")
		{
			for (i=0; i < name.length; i++)
			{
				if (isWhitespace(name[i]))
				{
					throw new InvalidNameException("No whitespace allowed in team names.");
				}
			}

			for (i=0; i<this.teams.length; i++)
			{
				if (this.teams[i].getName() == name)
				{
					throw new IllegalNameException("This team name is already taken.");
				}
			}

			Team team = new Team(name, description);
		}
		else
		{
            throw new InvalidNameException("Team name length is of incorrect length (1 to 30 characters) or is null.");
		}
		return -1; // !
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		for (i=0; i<this.teams.length; i++)
		{
			if (this.teams[i].getId() == teamId)
			{
				this.teams.remove(i);
				return;
			}
			throw new IDNotRecognisedException("No teams have the ID provided.")
		}

	}

	@Override
	public int[] getTeams() {
		// ! annoying return type
		int[] teamIds = new int[this.teams.length];
		for (i=0; i<this.teams.length; i++)
		{
			teamIds.add(this.teams[i]);
		}
		return teamIds;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		int[] teamRiders = new int[this.riders.length]; // Set the length
		for (i=0; i<this.riders.length; i++)
		{
			if (this.riders[i].getTeamId() == teamId)
			{
				throw new IDNotRecognisedException("No teams have this ID.")
				// code end
			}


		}
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		//
		if (yearOfBirth >= 1900)
		{
			if (name != null)
			{
				for (i=0; i<this.teams.length; i++)
				{
					if (this.teams[i].getName() == name)
					{
						throw new IllegalNameException("This name is already taken.");
						// code end
					}
				}

				Rider rider = new Rider(teamID, name, yearOfBirth);
				this.riders.add(rider);
				return rider.getId();
			}
			else
			{
				throw new IllegalArgumentException("Name of the rider cannot be null.");
			}
		}
		else
		{
			throw new IllegalArgumentException("Year of birth must be at least 1900.")
		}
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
        // Iteratively searches for the first (and only) Rider with the given ID, and removes it.
        for (int i=0; i < riders.length; i++)
        {
            if (riders[i].getId() == riderId)
            {
                riders.remove(i);
            }
        }
	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eraseCyclingPortal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

}
