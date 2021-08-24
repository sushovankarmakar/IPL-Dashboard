import React from "react";
import {MatchDetailsCard} from "../component/MatchDetailsCard";
import {MatchSmallCard} from "../component/MatchSmallCard";

export const TeamPage = () => {
  return (
    <div className="TeamPage">
      <h1>Kolkata Knight Riders</h1>
      <MatchDetailsCard />
      <MatchSmallCard />
      <MatchSmallCard />
      <MatchSmallCard />
    </div>
  );
};
