import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { MatchDetailsCard } from "../component/MatchDetailsCard";
import { MatchSmallCard } from "../component/MatchSmallCard";
import { PieChart } from "react-minimal-pie-chart";

import "./TeamPage.scss";

export const TeamPage = () => {
  const [team, setTeam] = useState({ latestMatches: [] });
  const { teamName } = useParams();

  useEffect(() => {
    const rootUrl = process.env.REACT_APP_API_ROOT_URL;

    const fetchTeamDetails = async () => {
      const response = await fetch(`${rootUrl}/team/${teamName}`);
      const data = await response?.json();
      setTeam(data);
    };
    fetchTeamDetails();
  }, [teamName]);

  if (!team || !team.name) {
    return <h1>Team not found !!!</h1>;
  }

  const latestYear = process.env.REACT_APP_DATA_END_YEAR;

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
              color: "#a91409",
            },
            {
              title: "Wins " + team?.winPercentage,
              value: team?.winPercentage,
              color: "#359fb9",
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
        <MatchSmallCard key={index} teamName={team?.name} match={match} />
      ))}
      <div className="more-link">
        <Link to={`/teams/${teamName}/matches/${latestYear}`}>More {">"}</Link>
      </div>
    </div>
  );
};
