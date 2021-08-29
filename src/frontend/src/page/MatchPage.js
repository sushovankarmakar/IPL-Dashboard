import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { MatchDetailsCard } from "../component/MatchDetailsCard";
import { YearSelector } from "../component/YearSelector";

import "./MatchPage.scss";

export const MatchPage = () => {
  const [matches, setMatches] = useState([]);
  const { teamName, year } = useParams();

  useEffect(() => {
    const rootUrl = process.env.REACT_APP_API_ROOT_URL;

    const fetchMatches = async () => {
      const response = await fetch(`${rootUrl}/match/${teamName}?year=${year}`);
      const data = await response?.json();
      setMatches(data);
    };
    fetchMatches();
  }, [teamName, year]);

  return (
    <div className="match-page">
      <div className="year-selector">
        <h3>Select Year</h3>
        <YearSelector teamName={teamName} />
      </div>

      <div>
        <h1 className="page-heading">
          {teamName}, {year}
        </h1>
        {matches?.map((match, index) => (
          <MatchDetailsCard key={index} teamName={teamName} match={match} />
        ))}
      </div>
    </div>
  );
};
