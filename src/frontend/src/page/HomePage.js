import React, { useEffect, useState } from "react";
import { TeamTile } from "../component/TeamTile";

import "./HomePage.scss";

export const HomePage = () => {
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    const rootUrl = process.env.REACT_APP_API_ROOT_URL;

    const fetchMatches = async () => {
      const response = await fetch(`${rootUrl}/team`);
      const data = await response?.json();
      setTeams(data);
    };
    fetchMatches();
  }, []);

  return (
    <div className="home-page">
      <div className="header-section">
        <h1 className="app-name">IPL Dashboard</h1>
      </div>
      <div className="team-grid">
        {teams?.map((team, index) => (
          <TeamTile key={index} teamName={team.name} />
        ))}
      </div>
    </div>
  );
};
