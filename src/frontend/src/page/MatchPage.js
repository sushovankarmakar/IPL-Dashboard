import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { MatchDetailsCard } from "../component/MatchDetailsCard";

export const MatchPage = () => {
  const [matches, setMatches] = useState([]);
  const { teamName, year } = useParams();

  useEffect(() => {
    const fetchMatches = async () => {
      const response = await fetch(
        `http://localhost:8080/match/${teamName}?year=${year}`
      );
      const data = await response?.json();
      setMatches(data);
    };
    fetchMatches();
  }, [teamName, year]);

  return (
    <div className="MatchPage">
      <h1>Match Page</h1>
      {matches?.map((match, index) => (
        <>
          <MatchDetailsCard key={index} name={teamName} match={match} />
        </>
      ))}
    </div>
  );
};
