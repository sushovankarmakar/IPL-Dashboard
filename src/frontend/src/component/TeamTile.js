import React from "react";
import { Link } from "react-router-dom";

import "./TeamTile.scss";

export const TeamTile = ({ teamName }) => {
  return (
    <div className="team-tile">
      <Link to={`/teams/${teamName}`}>{teamName}</Link>
    </div>
  );
};
