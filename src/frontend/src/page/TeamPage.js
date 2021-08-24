import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { MatchDetailsCard } from "../component/MatchDetailsCard";
import { MatchSmallCard } from "../component/MatchSmallCard";

export const TeamPage = () => {
  const [team, setTeam] = useState({ latestMatches: [] });
  const { teamName } = useParams();

  useEffect(() => {
    const fetchMatches = async () => {
      const response = await fetch(`http://localhost:8080/team/${teamName}`);
      const data = await response?.json();
      setTeam(data);
    };
    fetchMatches();
  }, [teamName]);

  if (!team || !team.name) {
    return <h1>Team not found !!!</h1>;
  }

  return (
    <div className="TeamPage">
      <h1>{team?.name}</h1>

      <MatchDetailsCard name={team?.name} match={team?.latestMatches?.[0]} />

      {team?.latestMatches?.slice(1)?.map((match, index) => (
        <MatchSmallCard key={index} name={team?.name} match={match} />
      ))}
    </div>
  );
};
