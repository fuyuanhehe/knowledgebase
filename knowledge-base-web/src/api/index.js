import * as repo from './repo';
import * as community from './community';
import * as proj from './proj';
import * as act from './act';
import * as ass from './assess';
import * as sys from './sys';
import * as stat from './stat';
import * as global from './global';
import * as upload from './upload';

export default {
    ...repo,
    ...community,
    ...proj,
    ...act,
    ...ass,
    ...sys,
    ...stat,
    ...global,
    ...upload
};
