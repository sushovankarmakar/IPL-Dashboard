import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { MatchDetailsCard } from "../component/MatchDetailsCard";
import { MatchSmallCard } from "../component/MatchSmallCard";
import { PieChart } from "react-minimal-pie-chart";

import "./TeamPage.scss";

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
    <div className="team-page">
      <div className="team-name-section">
        <h1 className="team-name">{team?.name}</h1>
      </div>

      <div className="win-loss-section">
        <span>Wins / Losses</span>
        <PieChart
          data={[
            {
              title: "Losses " + (100 - team?.winPercentage),
              value: 100 - team?.winPercentage,
              color: "#6a042a",
            },
            {
              title: "Wins " + team?.winPercentage,
              value: team?.winPercentage,
              color: "#046a56",
            },
          ]}
        />
      </div>

      <div className="match-details-section">
        <h3>Latest Matches</h3>
        <MatchDetailsCard
          teamName={team?.name}
          match={team?.latestMatches?.[0]}
        />
      </div>

      {team?.latestMatches?.slice(1)?.map((match, index) => (
        <div>
          <MatchSmallCard key={index} teamName={team?.name} match={match} />
        </div>
      ))}
      <div className="more-link">
        <a href="#">More {">"}</a>
      </div>
    </div>
  );
};
