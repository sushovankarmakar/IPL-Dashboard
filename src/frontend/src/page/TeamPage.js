import React, { useEffect, useState } from "react";
import { MatchDetailsCard } from "../component/MatchDetailsCard";
import { MatchSmallCard } from "../component/MatchSmallCard";

export const TeamPage = () => {
  const [team, setTeam] = useState({ latestMatches: [] });

  useEffect(() => {
    const fetchMatches = async () => {
      const response = await fetch(
        "http://localhost:8080/team/Rajasthan%20Royals"
      );
      const data = await response.json();
      setTeam(data);
    };
    fetchMatches();
  }, []);

  return (
    <div className="TeamPage">
      <h1>{team?.name}</h1>

      <MatchDetailsCard match={team?.latestMatches?.[0]} />

      {team?.latestMatches?.slice(1)?.map((match, index) => (
        <MatchSmallCard key={index} match={match} />
      ))}
    </div>
  );
};
