package com.jptravel.common;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.meshtiles.android.entity.Comment;
import com.meshtiles.android.entity.Country;
import com.meshtiles.android.entity.FavourisTagsNews;
import com.meshtiles.android.entity.Filter;
import com.meshtiles.android.entity.FilterLock;
import com.meshtiles.android.entity.Friend;
import com.meshtiles.android.entity.LogoutInfo;
import com.meshtiles.android.entity.Notify;
import com.meshtiles.android.entity.Pennant;
import com.meshtiles.android.entity.Photo;
import com.meshtiles.android.entity.PhotoLink;
import com.meshtiles.android.entity.Place;
import com.meshtiles.android.entity.PostsOfWeek;
import com.meshtiles.android.entity.RatingTotal;
import com.meshtiles.android.entity.RatingWeeks;
import com.meshtiles.android.entity.ReportErrors;
import com.meshtiles.android.entity.Tag;
import com.meshtiles.android.entity.TagInfo;
import com.meshtiles.android.entity.TagNews;
import com.meshtiles.android.entity.TopPhotos;
import com.meshtiles.android.entity.User;
import com.meshtiles.android.entity.UserNews;
import com.meshtiles.android.entity.UserStatus;

/**
 * JsonPaser class
 * 
 * @author NamNT
 * 
 */
public class JsonPaser {

	public JsonPaser(Context context) {
	}

	/**
	 * ID: API1.1 Name: reportErrors ID: API3.2 Name: resetPassword ID: API6.2
	 * Name: registerYOUData ID: API6.3 Name: registerLinkToOtherServices ID:
	 * API6.8 Name: registerYOurTags ID: API6.9 Name: updateImageProfile ID:
	 * API6.10 Name: checkUserNameAndEmail ID: API6.12 Name:
	 * createNewsForFriendFromOtherService ID: API7.9 Name: registerPrivacy ID:
	 * API7.3 Name: updateUserProfile ID: API7.12 Name:
	 * approvedOrRejectContactRequest ID: API7.14 Name: registerNotification ID:
	 * API7.17 Name: logout ID: API8.6 Name: buttonClick ID: API8.4 Name: report
	 * ID: API8.5 Name: postComment ID: API8.15 Name: blockUser ID: API8.23
	 * Name: deleteComment ID: API8.24 Name: deletePhoto ID: API8.29 Name:
	 * cancelLikePhoto ID: API4.6 Name: registerPhotoLinkToOtherServices ID:
	 * API6.4 Name: registerFollowingUser ID: API8.16 Name: checkUserIsBlocked
	 * 
	 * @param json
	 *            String
	 * @return ReportErrors Entity
	 */
	public ReportErrors getReport(String json) {
		ReportErrors report = new ReportErrors();
		try {
			JSONObject jObject = new JSONObject(json);
			report.setIs_success(jObject.getBoolean("is_success"));
			report.setMessage(jObject.getString("message"));
			if (jObject.opt("is_blocked") != null) {
				report.setIs_blocked(jObject.getBoolean("is_blocked"));
			}
			if (jObject.opt("result") != null) {
				report.setResult(jObject.getInt("result"));
			}

		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return report;
	}

	/**
	 * ID: API1.2 Name: checkConnectionStatus
	 * 
	 * @param json
	 *            String
	 * @return true/false
	 */
	public boolean checkConnectionStatus(String json) {
		boolean isSuccess = false;
		try {
			JSONObject jObject = new JSONObject(json);
			isSuccess = jObject.getBoolean("is_success");
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return isSuccess;

	}

	/**
	 * ID: API1.3 Name: getTime
	 * 
	 * @param json
	 * @return datetime
	 */
	public String getTime(String json) {
		String time = null;
		try {
			JSONObject jObject = new JSONObject(json);
			time = jObject.getString("datetime");
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return time;
	}

	/**
	 * ID: API2.1 Name: getTimelinePhotoOfUserFollowing
	 * 
	 * @param json
	 * @return List User Entity
	 */
	public ArrayList<User> getTimelinePhotoOfUserFollowing(String json) {
		ArrayList<User> lstUser = new ArrayList<User>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray userArray = jObject.getJSONArray("user");
				User user = null;
				Photo photo = null;
				ArrayList<Photo> lstPhoto = null;
				JSONObject userJson = null;
				JSONArray arrayPhotoJson = null;
				JSONObject photoJson = null;
				for (int i = 0; i < userArray.length(); i++) {
					userJson = userArray.getJSONObject(i);
					user = new User();
					if (userJson.opt("user_name") != null) {
						user.setUser_name(userJson.getString("user_name"));
					}
					if (userJson.opt("url_image") != null) {
						user.setUrl_image(userJson.getString("url_image"));
					}
					if (userJson.opt("user_id") != null) {
						user.setUser_id(userJson.getString("user_id"));
					}
				  
					// create list photo of user
					arrayPhotoJson = userJson.getJSONArray("photo");
					lstPhoto = new ArrayList<Photo>();
					for (int j = 0; j < arrayPhotoJson.length(); j++) {
						photoJson = arrayPhotoJson.getJSONObject(j);
						photo = new Photo();
						if (photoJson.opt("photo_id") != null) {
							photo.setPhoto_id(photoJson.getString("photo_id"));
						}
						if (photoJson.opt("time_post") != null) {
							photo.setTime_post(photoJson.getLong("time_post"));
						}					
						if (photoJson.opt("url_thumb") != null) {
							photo.setUrl_thumb(photoJson.getString("url_thumb"));
						}
						
					// Editor NghiaTT
						if (photoJson.opt("latitude") != null) {
							photo.setLatitude(photoJson.getDouble("latitude"));
						}
						if (photoJson.opt("longitude") != null) {
							photo.setLongitude(photoJson.getDouble("longitude"));
						}
						//EndOf NghiaTT
						lstPhoto.add(photo);
					}

					user.setPhotos(lstPhoto);

					lstUser.add(user);
				}
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstUser;
	}
	//Editor NghiaTT
	public ArrayList<Photo> getListPhotoOfTimeLineUserFollowing(String json) {
		ArrayList<User> lstUser = new ArrayList<User>();
		ArrayList<Photo> lstPhoto = new ArrayList<Photo>() ;
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray userArray = jObject.getJSONArray("user");
				User user = null;
				Photo photo = null;
				
				JSONObject userJson = null;
				JSONArray arrayPhotoJson = null;
				JSONObject photoJson = null;
				for (int i = 0; i < userArray.length(); i++) {
					userJson = userArray.getJSONObject(i);
					user = new User();
					if (userJson.opt("user_name") != null) {
						user.setUser_name(userJson.getString("user_name"));
					}
					if (userJson.opt("url_image") != null) {
						user.setUrl_image(userJson.getString("url_image"));
					}
					if (userJson.opt("user_id") != null) {
						user.setUser_id(userJson.getString("user_id"));
					}
				  
					// create list photo of user
					arrayPhotoJson = userJson.getJSONArray("photo");
					lstPhoto = new ArrayList<Photo>();
					for (int j = 0; j < arrayPhotoJson.length(); j++) {
						photoJson = arrayPhotoJson.getJSONObject(j);
						photo = new Photo();
						if (photoJson.opt("photo_id") != null) {
							photo.setPhoto_id(photoJson.getString("photo_id"));
						}
						if (photoJson.opt("time_post") != null) {
							photo.setTime_post(photoJson.getLong("time_post"));
						}					
						if (photoJson.opt("url_thumb") != null) {
							photo.setUrl_thumb(photoJson.getString("url_thumb"));
						}
						
					// Editor NghiaTT
						if (photoJson.opt("latitude") != null) {
							photo.setLatitude(photoJson.getDouble("latitude"));
						}
						if (photoJson.opt("longitude") != null) {
							photo.setLongitude(photoJson.getDouble("longitude"));
						}
						//EndOf NghiaTT
						lstPhoto.add(photo);
					}

					user.setPhotos(lstPhoto);

					lstUser.add(user);
				}
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		
		return lstPhoto;
	}
//EndOf Editor NghiaTT
	/**
	 * ID: API3.1 Name: login ID: API6.1 Name: signUp ID: API6.14 Name:
	 * getUserAccesToken ID: API7.4 Name: getUserProfile ID: API7.5 Name:
	 * getYOUData ID: API8.3 Name: getUserViewDetail
	 * 
	 * @param json
	 * @return
	 */
	public User getUser(String json) {
		User user = new User();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				if (jObject.opt("user") != null) {
					jObject = jObject.getJSONObject("user");
				}
				if (jObject.opt("about") != null) {
					user.setAbout(jObject.getString("about"));
				}
				if (jObject.opt("birthday") != null) {
					user.setBirthday(jObject.getString("birthday"));
				}
				if (jObject.opt("block_user") != null) {
					user.setBlock_user(jObject.getBoolean("block_user"));
				}
				if (jObject.opt("blood_type") != null) {
					user.setBlood_type(jObject.getString("blood_type"));
				}
				if (jObject.opt("city") != null) {
					user.setCurrent_city(jObject.getString("city"));
				}
				if (jObject.opt("current_city") != null) {
					user.setCurrent_city(jObject.getString("current_city"));
				}
				if (jObject.opt("country") != null) {
					user.setCurrent_country(jObject.getString("country"));
				}
				if (jObject.opt("current_country") != null) {
					user.setCurrent_country(jObject
							.getString("current_country"));
				}
				if (jObject.opt("email") != null) {
					user.setEmail(jObject.getString("email"));
				}
				if (jObject.opt("facebook_id") != null) {
					user.setFacebook_id(jObject.getString("facebook_id"));
				}
				if (jObject.opt("facebook_token") != null) {
					user.setFacebook_token(jObject.getString("facebook_token"));
				}
				if (jObject.opt("first_name") != null) {
					user.setFirst_name(jObject.getString("first_name"));
				}
				if (jObject.opt("follow_request") != null) {
					user.setFollow_request(jObject.getBoolean("follow_request"));
				}
				if (jObject.opt("interested") != null) {
					user.setInterested(jObject.getString("interested"));
				}
				if (jObject.opt("is_blocked") != null) {
					user.setIs_blocked(jObject.getBoolean("is_blocked"));
				}
				if (jObject.opt("is_following") != null) {
					user.setIs_following(jObject.getBoolean("is_following"));
				}
				if (jObject.opt("is_private") != null) {
					if (jObject.opt("is_private") instanceof String) {
						user.setIs_private("1".equals(jObject
								.getString("is_private")));
					} else {
						user.setIs_private(jObject.getBoolean("is_private"));
					}
				}
				if (jObject.opt("is_request") != null) {
					user.setIs_request(jObject.getBoolean("is_request"));
				}
				if (jObject.opt("last_name") != null) {
					user.setLast_name(jObject.getString("last_name"));
				}
				if (jObject.opt("latitude") != null) {
					user.setLatitude(jObject.getDouble("latitude"));
				}
				if (jObject.opt("longitude") != null) {
					user.setLongitude(jObject.getDouble("longitude"));
				}
				if (jObject.opt("male") != null) {
					user.setMale(jObject.getString("male"));
				}
				if (jObject.opt("number_follower") != null) {
					user.setNumber_follower(jObject.getInt("number_follower"));
				}
				if (jObject.opt("number_following") != null) {
					user.setNumber_following(jObject.getInt("number_following"));
				}
				if (jObject.opt("number_Master") != null) {
					user.setNumber_Master(jObject.getInt("number_Master"));
				}
				if (jObject.opt("number_Pennant") != null) {
					user.setNumber_Pennant(jObject.getInt("number_Pennant"));
				}
				if (jObject.opt("number_post") != null) {
					user.setNumber_post(jObject.getInt("number_post"));
				}
				if (jObject.opt("number_Vangard") != null) {
					user.setNumber_Vangard(jObject.getInt("number_Vangard"));
				}
				if (jObject.opt("password") != null) {
					user.setPassword(jObject.getString("password"));
				}
				if (jObject.opt("phone") != null) {
					user.setPhone(jObject.getString("phone"));
				}
				if (jObject.opt("public_birthday") != null) {
					user.setPublic_birthday(jObject
							.getString("public_birthday"));
				}
				if (jObject.opt("public_gender") != null) {
					user.setPublic_gender(jObject.getString("public_gender"));
				}
				if (jObject.opt("twitter_id") != null) {
					user.setTwitter_id(jObject.getString("twitter_id"));
				}
				if (jObject.opt("twitter_secret") != null) {
					user.setTwitter_secret(jObject.getString("twitter_secret"));
				}
				if (jObject.opt("twitter_token") != null) {
					user.setTwitter_token(jObject.getString("twitter_token"));
				}
				if (jObject.opt("url_image") != null) {
					user.setUrl_image(jObject.getString("url_image"));
				}
				if (jObject.opt("user_id") != null) {
					user.setUser_id(jObject.getString("user_id"));
				}
				if (jObject.opt("user_name") != null) {
					user.setUser_name(jObject.getString("user_name"));
				}
				//For Oauth
				try {
					if (jObject.opt("access_token") != null) {
						user.setAccess_token(jObject.getString("access_token"));
					}
					if (jObject.opt("refresh_token") != null) {
						user.setRefresh_token(jObject.getString("refresh_token"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			} else {
				// show message when login error
				user.setMessage(jObject.getString("message"));
				if (jObject.getString("message").equals("ERR-BLOCKED")) {
					user.setIs_blocked(true);
				}
			}

		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return user;
	}

	// TODO: remove
	// /**
	// * ID: API3.1 Name: login
	// * @param json
	// * @return
	// */
	// public User login(String json) {
	// User user = new User();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if(jObject.getBoolean("is_success")){
	// user.setUser_id(jObject.getString("user_id"));
	// user.setUrl_image(jObject.getString("url_image"));
	// user.setCurrent_city(jObject.getString("city"));
	// user.setCurrent_country(jObject.getString("country"));
	// user.setLatitude(jObject.getDouble("latitude"));
	// user.setLongitude(jObject.getDouble("longitude"));
	// } else {
	// //show message when login error
	// user.setMessage(jObject.getString("message"));
	// }
	//
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return user;
	// }

	/**
	 * ID: API3.3 Name: getInfoWhenLogout
	 * 
	 * @param json
	 * @return LogoutInfo
	 */
	public LogoutInfo getInfoWhenLogout(String json) {
		LogoutInfo info = new LogoutInfo();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				if (jObject.opt("number_comment") != null) {
					info.setNumber_comment(jObject.getInt("number_comment"));
				}
				if (jObject.opt("number_rating") != null) {
					info.setNumber_rating(jObject.getInt("number_rating"));
				}
				if (jObject.opt("number_follow") != null) {
					info.setNumber_follow(jObject.getInt("number_follow"));
				}
			} else {
				// show message when error
				info.setMessage(jObject.getString("message"));
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return info;
	}

	/**
	 * ID: API5.1 Name: getListTrendTags
	 * 
	 * @param json
	 * @return
	 */
	public ArrayList<Tag> getListTrendTags(String json) {
		ArrayList<Tag> lstTag = new ArrayList<Tag>();
		try {
			JSONObject jObject = new JSONObject(json);

			if (jObject.getBoolean("is_success")) {
				JSONArray tagArray = jObject.getJSONArray("tag");
				JSONObject tagJson = null;
				Tag tag = null;
				for (int i = 0; i < tagArray.length(); i++) {
					tagJson = tagArray.getJSONObject(i);
					tag = new Tag();
					if (tagJson.opt("number_post") != null) {
						tag.setNumber_post(tagJson.getInt("number_post"));
					}
					if (tagJson.opt("tag_name") != null) {
						tag.setTag_name(tagJson.getString("tag_name"));
					}

					if (tagJson.opt("photo") != null) {
						JSONArray arrayPhotoJson = null;
						JSONObject photoJson = null;
						Photo photo = null;
						ArrayList<Photo> lstPhoto = new ArrayList<Photo>();
						// create list photo of tag
						arrayPhotoJson = tagJson.getJSONArray("photo");
						for (int j = 0; j < arrayPhotoJson.length(); j++) {
							photoJson = arrayPhotoJson.getJSONObject(j);
							photo = new Photo();
							if (photoJson.opt("photo_id") != null) {
								photo.setPhoto_id(photoJson
										.getString("photo_id"));
							}
							if (photoJson.opt("url_thumb") != null) {
								photo.setUrl_thumb(photoJson
										.getString("url_thumb"));
							}
							lstPhoto.add(photo);
						}
						tag.setPhoto(lstPhoto);
					}
					lstTag.add(tag);
				}

			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstTag;
	}

	/**
	 * ID: API5.2 Name: getTrendTagDetail
	 * 
	 * @param json
	 * @return Tag
	 */
	public Tag getTrendTagDetail(String json) {
		Tag tag = new Tag();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				jObject = jObject.getJSONObject("tag");
				if (jObject.opt("tag_name") != null) {
					tag.setTag_name(jObject.getString("tag_name"));
				}
				if (jObject.opt("number_post") != null) {
					tag.setNumber_post(jObject.getInt("number_post"));
				}
				if (jObject.opt("my_post") != null) {
					tag.setMy_post(jObject.getInt("my_post"));
				}
				if (jObject.opt("number_Vangard") != null) {
					tag.setNumber_Vangard(jObject.getInt("number_Vangard"));
				}
				if (jObject.opt("number_Master") != null) {
					tag.setNumber_Master(jObject.getInt("number_Master"));
				}
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return tag;
	}

	/**
	 * ID: API5.3 Name: getListUserByTitleAndTag ID: API6.6 Name:
	 * getListUserByUserName ID: API6.7 Name: getListUserByContactAddress ID:
	 * API6.13 Name: searchFriendFromOtherServices ID: API7.11 Name:
	 * getListContactRequest ID: API8.22 Name: getListUserRatingPhoto ID:
	 * API8.14 Name: getListUserFollow
	 * 
	 * @param json
	 * @return ArrayList<User>
	 */
	public ArrayList<User> getListUser(String json) {
		ArrayList<User> lstUser = new ArrayList<User>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray userArray = jObject.getJSONArray("user");
				User user = null;
				JSONObject userJson = null;
				for (int i = 0; i < userArray.length(); i++) {
					userJson = userArray.getJSONObject(i);
					user = new User();

					if (userJson.opt("about") != null) {
						user.setAbout(userJson.getString("about"));
					}
					if (userJson.opt("first_name") != null) {
						user.setFirst_name(userJson.getString("first_name"));
					}
					if (userJson.opt("is_blocked") != null) {
						user.setIs_blocked(userJson.getBoolean("is_blocked"));
					}
					if (userJson.opt("is_following") != null) {
						user.setIs_following(userJson
								.getBoolean("is_following"));
					}
					if (userJson.opt("is_private") != null) {
						user.setIs_private(userJson.getBoolean("is_private"));
					}
					if (userJson.opt("is_request") != null) {
						user.setIs_request(userJson.getBoolean("is_request"));
					}
					if (userJson.opt("last_name") != null) {
						user.setLast_name(userJson.getString("last_name"));
					}
					if (userJson.opt("url_image") != null) {
						user.setUrl_image(userJson.getString("url_image"));
					}
					if (userJson.opt("user_id") != null) {
						user.setUser_id(userJson.getString("user_id"));
					}
					if (userJson.opt("user_name") != null) {
						user.setUser_name(userJson.getString("user_name"));
					}
					lstUser.add(user);
				}
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstUser;
	}

	// TODO: remove
	// /**
	// * ID: API5.3 Name: getListUserByTitleAndTag
	// * @param json
	// * @return
	// */
	// public ArrayList<User> getListUserByTitleAndTag(String json) {
	// ArrayList<User> lstUser = new ArrayList<User>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if (jObject.getBoolean("is_success")) {
	// JSONArray userArray = jObject.getJSONArray("user");
	// User user = null;
	// JSONObject userJson = null;
	// for (int i = 0; i < userArray.length(); i++) {
	// userJson = userArray.getJSONObject(i);
	// user = new User();
	// user.setUser_name(userJson.getString("user_name"));
	// user.setFirst_name(userJson.getString("first_name"));
	// user.setLast_name(userJson.getString("last_name"));
	// user.setUser_id(userJson.getString("user_id"));
	// user.setIs_following(userJson.getBoolean("is_following"));
	// user.setIs_private(userJson.getBoolean("is_private"));
	// user.setIs_request(userJson.getBoolean("is_request"));
	// user.setIs_blocked(userJson.getBoolean("is_blocked"));
	// user.setUrl_image(userJson.getString("url_image"));
	// user.setAbout(userJson.getString("about"));
	// lstUser.add(user);
	// }
	// }
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return lstUser;
	// }

	/**
	 * ID: API5.4 Name: getListUserPhotoByTags ID: API8.20 Name:
	 * getListPopularPhoto ID: API8.10 Name: getListPhotoByYOU ID: API8.27 Name:
	 * getListPhotoByLocationID ID: API8.25 Name: getPhotoOfUser
	 * 
	 * @param json
	 * @return ArrayList<Photo>
	 */
	public ArrayList<Photo> getListPhoto(String json) {
		ArrayList<Photo> lstPhoto = new ArrayList<Photo>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray photoArray = jObject.getJSONArray("photo");
				Photo photo = null;
				JSONObject photoJson = null;
				for (int i = 0; i < photoArray.length(); i++) {
					photoJson = photoArray.getJSONObject(i);
					photo = new Photo();
					if (photoJson.opt("photo_id") != null) {
						photo.setPhoto_id(photoJson.getString("photo_id"));
					}
					if (photoJson.opt("url_thumb") != null) {
						photo.setUrl_thumb(photoJson.getString("url_thumb"));
					}
					if (photoJson.opt("latitude") != null) {
						photo.setLatitude(photoJson.getDouble("latitude"));
					}
					if (photoJson.opt("longitude") != null) {
						photo.setLongitude(photoJson.getDouble("longitude"));
					}
					lstPhoto.add(photo);
				}
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstPhoto;
	}

	// TODO: remove
	// /**
	// * ID: API5.4 Name: getListUserPhotoByTags
	// * @param json
	// * @return
	// */
	// public ArrayList<Photo> getListUserPhotoByTags(String json) {
	// ArrayList<Photo> lstPhoto = new ArrayList<Photo>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if (jObject.getBoolean("is_success")) {
	// JSONArray photoArray = jObject.getJSONArray("photo");
	// Photo photo = null;
	// JSONObject photoJson = null;
	// for (int i = 0; i < photoArray.length(); i++) {
	// photoJson = photoArray.getJSONObject(i);
	// photo = new Photo();
	// photo.setPhoto_id(photoJson.getString("photo_id"));
	// photo.setUrl_thumb(photoJson.getString("url_thumb"));
	// photo.setLatitude(photoJson.getDouble("latitude"));
	// photo.setLongitude(photoJson.getDouble("longitude"));
	// lstPhoto.add(photo);
	// }
	// }
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return lstPhoto;
	// }

	/**
	 * ID: API6.1 Name: signUp
	 * 
	 * @param json
	 * @return User
	 */
	public User signUp(String json) {
		User user = new User();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				user.setUser_id(jObject.getString("user_id"));
				try {
					if (jObject.opt("access_token") != null) {
						user.setAccess_token(jObject.getString("access_token"));
					}
					if (jObject.opt("refresh_token") != null) {
						user.setRefresh_token(jObject.getString("refresh_token"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			} else {
				// show message when login error
				user.setMessage(jObject.getString("message"));
			  
			}

		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return user;
	}

	// TODO: remove
	// /**
	// * ID: API6.4 Name: registerFollowingUser
	// * @param json String
	// * @return ReportErrors Entity
	// */
	// public ReportErrors registerFollowingUser(String json) {
	// ReportErrors report = new ReportErrors();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// report.setIs_success(jObject.getBoolean("is_success"));
	// report.setMessage(jObject.getString("message"));
	// report.setResult(jObject.getInt("result"));
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return report;
	// }

	/**
	 * ID: API6.5 Name: getListUserRecommended
	 * 
	 * @param json
	 * @return
	 */
	public ArrayList<User> getListUserRecommended(String json) {
		ArrayList<User> lstUser = new ArrayList<User>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray userArray = jObject.getJSONArray("user");
				User user = null;
				JSONObject userJson = null;
				Photo photo = null;
				ArrayList<Photo> lstPhoto = null;
				JSONArray arrayPhotoJson = null;
				JSONObject photoJson = null;

				for (int i = 0; i < userArray.length(); i++) {
					userJson = userArray.getJSONObject(i);
					user = new User();
					if (userJson.opt("user_name") != null) {
						user.setUser_name(userJson.getString("user_name"));
					}
					if (userJson.opt("first_name") != null) {
						user.setFirst_name(userJson.getString("first_name"));
					}
					if (userJson.opt("last_name") != null) {
						user.setLast_name(userJson.getString("last_name"));
					}
					if (userJson.opt("user_id") != null) {
						user.setUser_id(userJson.getString("user_id"));
					}
					if (userJson.opt("is_following") != null) {
						user.setIs_following(userJson
								.getBoolean("is_following"));
					}
					if (userJson.opt("is_private") != null) {
						user.setIs_private(userJson.getBoolean("is_private"));
					}
					if (userJson.opt("is_request") != null) {
						user.setIs_request(userJson.getBoolean("is_request"));
					}
					if (userJson.opt("url_image") != null) {
						user.setUrl_image(userJson.getString("url_image"));
					}
					if (userJson.opt("about") != null) {
						user.setAbout(userJson.getString("about"));
					}

					// create list photo of user
					arrayPhotoJson = userJson.getJSONArray("photo");
					lstPhoto = new ArrayList<Photo>();
					for (int j = 0; j < arrayPhotoJson.length(); j++) {
						photoJson = arrayPhotoJson.getJSONObject(j);
						photo = new Photo();
						if (photoJson.opt("photo_id") != null) {
							photo.setPhoto_id(photoJson.getString("photo_id"));
						}
						if (photoJson.opt("url_thumb") != null) {
							photo.setUrl_thumb(photoJson.getString("url_thumb"));
						}
						lstPhoto.add(photo);
					}

					user.setPhotos(lstPhoto);
					lstUser.add(user);
				}
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstUser;
	}

	// TODO: remove
	// /**
	// * ID: API6.6 Name: getListUserByUserName
	// * @param json
	// * @return List User
	// */
	// public ArrayList<User> getListUserByUserName(String json) {
	// ArrayList<User> lstUser = new ArrayList<User>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if (jObject.getBoolean("is_success")) {
	// JSONArray userArray = jObject.getJSONArray("user");
	// User user = null;
	// JSONObject userJson = null;
	// for (int i = 0; i < userArray.length(); i++) {
	// userJson = userArray.getJSONObject(i);
	// user = new User();
	// user.setUser_name(userJson.getString("user_name"));
	// user.setFirst_name(userJson.getString("first_name"));
	// user.setLast_name(userJson.getString("last_name"));
	// user.setUser_id(userJson.getString("user_id"));
	// user.setIs_following(userJson.getBoolean("is_following"));
	// user.setIs_private(userJson.getBoolean("is_private"));
	// user.setIs_request(userJson.getBoolean("is_request"));
	// user.setUrl_image(userJson.getString("url_image"));
	// lstUser.add(user);
	// }
	// }
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return lstUser;
	// }

	// TODO: Remove
	// /**
	// * ID: API6.7 Name: getListUserByContactAddress
	// * @param json
	// * @return List User
	// */
	// public ArrayList<User> getListUserByContactAddress(String json) {
	// ArrayList<User> lstUser = new ArrayList<User>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if (jObject.getBoolean("is_success")) {
	// JSONArray userArray = jObject.getJSONArray("user");
	// User user = null;
	// JSONObject userJson = null;
	// for (int i = 0; i < userArray.length(); i++) {
	// userJson = userArray.getJSONObject(i);
	// user = new User();
	// user.setUser_name(userJson.getString("user_name"));
	// user.setFirst_name(userJson.getString("first_name"));
	// user.setLast_name(userJson.getString("last_name"));
	// user.setUser_id(userJson.getString("user_id"));
	// user.setIs_following(userJson.getBoolean("is_following"));
	// user.setIs_private(userJson.getBoolean("is_private"));
	// user.setIs_request(userJson.getBoolean("is_request"));
	// user.setUrl_image(userJson.getString("url_image"));
	// lstUser.add(user);
	// }
	// }
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return lstUser;
	// }

	/**
	 * ID: API6.11 Name: getListCountry
	 * 
	 * @param json
	 * @return List County
	 */
	public ArrayList<Country> getListCountry(String json) {
		ArrayList<Country> lstCountry = new ArrayList<Country>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray countryArray = jObject.getJSONArray("country");
				JSONObject countryJson = null;
				Country country = null;
				for (int i = 0; i < countryArray.length(); i++) {
					countryJson = countryArray.getJSONObject(i);
					country = new Country();
					country.setCountry_name(countryJson
							.getString("country_name"));
					lstCountry.add(country);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			// TODO: nothing
			// // Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstCountry;
	}

	// TODO: remove
	// /**
	// * ID: API6.13 Name: searchFriendFromOtherServices
	// * @param json
	// * @return List User
	// */
	// public ArrayList<User> searchFriendFromOtherServices(String json) {
	// ArrayList<User> lstUser = new ArrayList<User>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if (jObject.getBoolean("is_success")) {
	// JSONArray userArray = jObject.getJSONArray("user");
	// User user = null;
	// JSONObject userJson = null;
	// for (int i = 0; i < userArray.length(); i++) {
	// userJson = userArray.getJSONObject(i);
	// user = new User();
	// user.setUser_name(userJson.getString("user_name"));
	// user.setFirst_name(userJson.getString("first_name"));
	// user.setLast_name(userJson.getString("last_name"));
	// user.setUser_id(userJson.getString("user_id"));
	// user.setIs_following(userJson.getBoolean("is_following"));
	// user.setIs_private(userJson.getBoolean("is_private"));
	// user.setIs_request(userJson.getBoolean("is_request"));
	// user.setUrl_image(userJson.getString("url_image"));
	// lstUser.add(user);
	// }
	// }
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return lstUser;
	// }

	// TODO: Remove
	// /**
	// * ID: API6.14 Name: getUserAccesToken
	// * @param json
	// * @return
	// */
	// public User getUserAccesToken(String json) {
	// User user = new User();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if(jObject.getBoolean("is_success")){
	// user.setUser_id(jObject.getString("user_id"));
	// user.setFacebook_id(jObject.getString("facebook_id"));
	// user.setFacebook_token(jObject.getString("facebook_token"));
	// user.setTwitter_id(jObject.getString("twitter_id"));
	// user.setTwitter_token(jObject.getString("twitter_token"));
	// user.setTwitter_secret(jObject.getString("twitter_secret"));
	// } else {
	// //show message when login error
	// user.setMessage(jObject.getString("message"));
	// }
	//
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return user;
	// }

	/**
	 * Hàm lấy next token để request trang tiếp theo
	 * @param json Chuỗi json do server trả về có chứa next_token
	 * @return Next token để truyền lên khi reuqest trang tiếp theo, nếu next_token là 1 chuỗi rỗng thì kết news
	 */
	public String getNextTokenUserNews(String json){
		String nextToken ="";
		try {
			JSONObject jObject = new JSONObject(json);
			if(jObject.getBoolean("is_success")){
				nextToken = jObject.getString("next_token");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nextToken;
	}
	
	/**
	 * ID: API7.7 Name: getUserNews
	 * 
	 * @param json
	 * @return List UserNews
	 */
	public ArrayList<UserNews> getUserNews(String json) {
		ArrayList<UserNews> lstUserNews = new ArrayList<UserNews>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray newsArray = jObject.getJSONArray("news");
				UserNews news = null;
				JSONObject newsJson = null;
				for (int i = 0; i < newsArray.length(); i++) {
					newsJson = newsArray.getJSONObject(i);
					news = new UserNews();
					if (newsJson.opt("type") != null) {
						news.setType(newsJson.getInt("type"));
					}
					if (newsJson.opt("time") != null) {
						news.setTime(newsJson.getLong("time"));
					}
					if (newsJson.opt("url_image") != null) {
						news.setUrl_image(newsJson.getString("url_image"));
					}
					if (newsJson.opt("content") != null) {
						news.setContent(newsJson.getString("content"));
					}
					if (newsJson.opt("object_id") != null) {
						news.setObject_id(newsJson.getString("object_id"));
					}
					if (newsJson.opt("user_name") != null) {
						news.setUser_name(newsJson.getString("user_name"));
					}
					if (newsJson.opt("user_id") != null) {
						news.setUser_id(newsJson.getString("user_id"));
					}
					if (newsJson.opt("url_avatar") != null) {
						news.setUrl_avatar(newsJson.getString("url_avatar"));
					}
					lstUserNews.add(news);
				}
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstUserNews;
	}

	/**
	 * ID: API7.8 Name: getNewsOfFavourisTags
	 * 
	 * @param json
	 * @return List FavourisTagsNews
	 */
	public ArrayList<FavourisTagsNews> getNewsOfFavourisTags(String json) {
		ArrayList<FavourisTagsNews> lstTagNews = new ArrayList<FavourisTagsNews>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray newsArray = jObject.getJSONArray("news");
				FavourisTagsNews news = null;
				JSONObject newsJson = null;
				for (int i = 0; i < newsArray.length(); i++) {
					newsJson = newsArray.getJSONObject(i);
					news = new FavourisTagsNews();
					if (newsJson.opt("user_name") != null) {
						news.setUser_name(newsJson.getString("user_name"));
					}
					if (newsJson.opt("type") != null) {
						news.setType(newsJson.getInt("type"));
					}
					if (newsJson.opt("time") != null) {
						news.setTime(newsJson.getLong("time"));
					}
					if (newsJson.opt("tag_name") != null) {
						news.setTag_name(newsJson.getString("tag_name"));
					}
					if (newsJson.opt("object_id") != null) {
						news.setObject_id(newsJson.getString("object_id"));
					}
					if (newsJson.opt("object_name") != null) {
						news.setObject_name(newsJson.getString("object_name"));
					}
					if (newsJson.opt("object_url") != null) {
						news.setObject_url(newsJson.getString("object_url"));
					}
					if (newsJson.opt("content") != null) {
						news.setContent(newsJson.getString("content"));
					}
					lstTagNews.add(news);
				}
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstTagNews;
	}

	/**
	 * ID: API7.1 Name: getListPennantsByUser
	 * 
	 * @param json
	 * @return
	 */
	public ArrayList<Pennant> getListPennantsByUser(String json) {
		ArrayList<Pennant> lstPennant = new ArrayList<Pennant>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray penArray = jObject.getJSONArray("pennant");
				Pennant pen = null;
				JSONObject penJson = null;
				for (int i = 0; i < penArray.length(); i++) {
					penJson = penArray.getJSONObject(i);
					pen = new Pennant();
					if (penJson.opt("id") != null) {
						pen.setId(penJson.getInt("id"));
					}
					if (penJson.opt("name") != null) {
						pen.setName(penJson.getString("name"));
					}
					if (penJson.opt("url_image") != null) {
						pen.setUrl_image(penJson.getString("url_image"));
					}
					lstPennant.add(pen);
				}
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstPennant;

	}

	/**
	 * ID: API7.2 Name: getPennantsDetail
	 * 
	 * @param json
	 * @return
	 */
	public Pennant getPennantsDetail(String json) {
		Pennant pen = new Pennant();
		try {
			JSONObject jObject = new JSONObject(json);
			JSONObject photoJson = null;
			JSONObject penJson = null;
			Photo photo = null;

			// DuyTx : Sua lay unlock 29-02-2012
			if (jObject.opt("unlock") != null) {
				pen.setUnlock(jObject.getBoolean("unlock"));
			}
			if (jObject.getBoolean("is_success")) {
				penJson = jObject.getJSONObject("pennant");
				if (penJson.opt("id") != null) {
					pen.setId(penJson.getInt("id"));
				}
				if (penJson.opt("name") != null) {
					pen.setName(penJson.getString("name"));
				}
				if (penJson.opt("url_image") != null) {
					pen.setUrl_image(penJson.getString("url_image"));
				}
				if (penJson.opt("message") != null) {
					pen.setMessage(penJson.getString("message"));
					Log.d("message", penJson.getString("message"));
				}
				if (penJson.opt("time_unlock") != null) {
					pen.setTime_unlock(penJson.getLong("time_unlock"));
				}
				if (penJson.opt("client_time_unlock") != null) {
					pen.setClient_time_unlock(penJson
							.getString("client_time_unlock"));
				}
				if (penJson.opt("hint") != null) {
					pen.setHint(penJson.getString("hint"));
				}
				// if (penJson.opt("unlock") != null) {
				// pen.setUnlock(jObject.getBoolean("unlock"));
				// }

				// set photo unlock pennant
				photoJson = penJson.getJSONObject("photo");
				photo = new Photo();
				if (photoJson.opt("photo_id") != null) {
					photo.setPhoto_id(photoJson.getString("photo_id"));
				}
				if (photoJson.opt("url_thumb") != null) {
					photo.setUrl_thumb(photoJson.getString("url_thumb"));
				}
				pen.setPhoto(photo);
			}
		} catch (JSONException e) {
			// TODO: nothing
			// // Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return pen;
	}

	// TODO: Remove
	// /**
	// * ID: API7.4 Name: getUserProfile
	// * @param json
	// * @return
	// */
	// public User getUserProfile(String json) {
	// User user = new User();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if(jObject.getBoolean("is_success")){
	// user.setUser_id(jObject.getString("user_id"));
	// user.setUser_name(jObject.getString("user_name"));
	// user.setPassword(jObject.getString("password"));
	// user.setEmail(jObject.getString("email"));
	// user.setPhone(jObject.getString("phone"));
	// user.setFirst_name(jObject.getString("first_name"));
	// user.setLast_name(jObject.getString("last_name"));
	// user.setAbout(jObject.getString("about"));
	// } else {
	// //show message when login error
	// user.setMessage(jObject.getString("message"));
	// }
	//
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return user;
	// }

	// TODO: Remove
	// /**
	// * ID: API7.5 Name: getYOUData
	// * @param json
	// * @return
	// */
	// public User getYOUData(String json) {
	// User user = new User();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if(jObject.getBoolean("is_success")){
	// user.setUser_id(jObject.getString("user_id"));
	// user.setMale(jObject.getString("male"));
	// user.setPublic_gender(jObject.getString("public_gender"));
	// user.setInterested(jObject.getString("interested"));
	// user.setPublic_birthday(jObject.getString("public_birthday"));
	// user.setCurrent_city(jObject.getString("current_city"));
	// user.setCurrent_country(jObject.getString("current_country"));
	// user.setBlood_type(jObject.getString("blood_type"));
	// user.setBirthday(jObject.getString("birthday"));
	// user.setLatitude(jObject.getDouble("latitude"));
	// user.setLongitude(jObject.getDouble("longitude"));
	// user.setAbout(jObject.getString("about"));
	// } else {
	// //show message when login error
	// user.setMessage(jObject.getString("message"));
	// }
	//
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return user;
	// }

	/**
	 * ID: API7.10 Name: checkPrivacy
	 * 
	 * @param json
	 * @return true/false
	 */
	public boolean checkPrivacy(String json) {
		boolean isPrivate = false;
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")
					&& jObject.getBoolean("is_private")) {
				isPrivate = true;
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return isPrivate;
	}

	// TODO: Remove
	// /**
	// * ID: API7.11 Name: getListContactRequest
	// * @param json
	// * @return
	// */
	// public ArrayList<User> getListContactRequest(String json) {
	// ArrayList<User> lstUser = new ArrayList<User>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if (jObject.getBoolean("is_success")) {
	// JSONArray userArray = jObject.getJSONArray("user");
	// User user = null;
	// JSONObject userJson = null;
	// for (int i = 0; i < userArray.length(); i++) {
	// userJson = userArray.getJSONObject(i);
	// user = new User();
	// user.setUser_name(userJson.getString("user_name"));
	// user.setFirst_name(userJson.getString("first_name"));
	// user.setLast_name(userJson.getString("last_name"));
	// user.setUser_id(userJson.getString("user_id"));
	// user.setUrl_image(userJson.getString("url_image"));
	// lstUser.add(user);
	// }
	// }
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return lstUser;
	// }

	/**
	 * ID: API7.15 Name: getNotification
	 * 
	 * @param json
	 * @return
	 */
	public Notify getNotification(String json) {
		Notify notify = new Notify();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				if (jObject.opt("user_id") != null) {
					notify.setUser_id(jObject.getString("user_id"));
				}
				if (jObject.opt("nof_button") != null) {
					notify.setNof_button(jObject.getInt("nof_button"));
				}
				if (jObject.opt("nof_comment") != null) {
					notify.setNof_comment(jObject.getInt("nof_comment"));
				}
				if (jObject.opt("nof_contact") != null) {
					notify.setNof_contact(jObject.getInt("nof_contact"));
				}
				if (jObject.opt("face_title") != null) {
					notify.setFace_title(jObject.getInt("face_title"));
				}
				if (jObject.opt("twitter_title") != null) {
					notify.setTwitter_title(jObject.getInt("twitter_title"));
				}
				if (jObject.opt("face_pennant") != null) {
					notify.setFace_pennant(jObject.getInt("face_pennant"));
				}
				if (jObject.opt("twitter_pennant") != null) {
					notify.setTwitter_pennant(jObject.getInt("twitter_pennant"));
				}
				if (jObject.opt("lost_title") != null) {
					notify.setLost_title(jObject.getInt("lost_title"));
				}
			}
		} catch (JSONException e) {
			// TODO: nothing
			// // Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return notify;
	}

	/**
	 * ID: API7.16 Name: getAllPennants
	 * 
	 * @param json
	 * @return
	 */
	public ArrayList<Pennant> getAllPennants(String json) {
		ArrayList<Pennant> lstPen = new ArrayList<Pennant>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray penArray = jObject.getJSONArray("pennant");
				Pennant pen = null;
				JSONObject penJson = null;
				for (int i = 0; i < penArray.length(); i++) {
					penJson = penArray.getJSONObject(i);
					pen = new Pennant();
					if (penJson.opt("id") != null) {
						pen.setId(penJson.getInt("id"));
					}
					if (penJson.opt("name") != null) {
						pen.setName(penJson.getString("name"));
					}
					if (penJson.opt("url_image") != null) {
						pen.setUrl_image(penJson.getString("url_image"));
					}
					if (penJson.opt("note") != null) {
						pen.setNote(penJson.getString("note"));
					}
					lstPen.add(pen);
				}
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstPen;
	}

	// TODO: Remove
	// /**
	// * ID: API8.22 Name: getListUserRatingPhoto
	// * @param json
	// * @return
	// */
	// public ArrayList<User> getListUserRatingPhoto(String json) {
	// ArrayList<User> lstUser = new ArrayList<User>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if (jObject.getBoolean("is_success")) {
	// JSONArray userArray = jObject.getJSONArray("user");
	// User user = null;
	// JSONObject userJson = null;
	// for (int i = 0; i < userArray.length(); i++) {
	// userJson = userArray.getJSONObject(i);
	// user = new User();
	// user.setUser_name(userJson.getString("user_name"));
	// user.setFirst_name(userJson.getString("first_name"));
	// user.setLast_name(userJson.getString("last_name"));
	// user.setUser_id(userJson.getString("user_id"));
	// user.setIs_following(userJson.getBoolean("is_following"));
	// user.setIs_request(userJson.getBoolean("is_request"));
	// user.setUrl_image(userJson.getString("url_image"));
	// lstUser.add(user);
	// }
	// }
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return lstUser;
	// }

	/**
	 * ID: API8.2 Name: getPhotoDetail
	 * 
	 * @param json
	 * @return
	 */
	public Photo getPhotoDetail(String json) {
		Photo photo = new Photo();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				jObject = jObject.getJSONObject("photo");

				if (jObject.opt("photo_id") != null) {
					photo.setPhoto_id(jObject.getString("photo_id"));
				}
				if (jObject.opt("button_type") != null) {
					photo.setButton_type(jObject.getInt("button_type"));
				}
				if (jObject.opt("number_click") != null) {
					photo.setNumber_click(jObject.getInt("number_click"));
				}
				if (jObject.opt("url_photo") != null) {
					photo.setUrl_photo(jObject.getString("url_photo"));
				}

				// comment
				JSONArray commentArr = jObject.getJSONArray("comment");
				Comment comment = null;
				JSONObject comJson = null;
				ArrayList<Comment> lstComment = new ArrayList<Comment>();
				for (int i = 0; i < commentArr.length(); i++) {
					comJson = commentArr.getJSONObject(i);
					comment = new Comment();
					if (comJson.opt("comment_id") != null) {
						comment.setComment_id(comJson.getString("comment_id"));
					}
					if (comJson.opt("content") != null) {
						comment.setContent(comJson.getString("content"));
					}
					if (comJson.opt("time_post") != null) {
						comment.setTime_post(comJson.getLong("time_post"));
					}
					if (comJson.opt("user_id") != null) {
						comment.setUser_id(comJson.getString("user_id"));
					}
					if (comJson.opt("user_name") != null) {
						comment.setUser_name(comJson.getString("user_name"));
					}
					if (comJson.opt("url_image") != null) {
						comment.setUrl_image(comJson.getString("url_image"));
					}

					lstComment.add(comment);
				}
				photo.setComment(lstComment);
				if (jObject.opt("number_comment") != null) {
					photo.setNumber_comment(jObject.getInt("number_comment"));
				}
				if (jObject.opt("list_tags") != null) {
					photo.setList_tags(jObject.getString("list_tags"));
				}
				if (jObject.opt("location_text") != null) {
					photo.setLocation_text(jObject.getString("location_text"));
				}
				if (jObject.opt("location_id") != null) {
					photo.setLocation_id(jObject.getString("location_id"));
				}
				if (jObject.opt("longitude") != null) {
					photo.setLongitude(jObject.getDouble("longitude"));
				}
				if (jObject.opt("latitude") != null) {
					photo.setLatitude(jObject.getDouble("latitude"));
				}
				if (jObject.opt("location_longitude") != null) {
					photo.setLocation_longitude(jObject
							.getDouble("location_longitude"));
				}
				if (jObject.opt("location_latitude") != null) {
					photo.setLocation_latitude(jObject
							.getDouble("location_latitude"));
				}

				// user
				JSONObject userJson = jObject.getJSONObject("user");
				User user = new User();
				if (userJson.opt("user_name") != null) {
					user.setUser_name(userJson.getString("user_name"));
				}
				if (userJson.opt("user_id") != null) {
					user.setUser_id(userJson.getString("user_id"));
				}
				if (userJson.opt("url_image") != null) {
					user.setUrl_image(userJson.getString("url_image"));
				}
				if (userJson.opt("is_following") != null) {
					user.setIs_following(userJson.getBoolean("is_following"));
				}
				photo.setUser(user);

				// friend
				JSONArray friendArr = jObject.getJSONArray("friend");
				Friend friend = null;
				JSONObject friendJson = null;
				ArrayList<Friend> lstFriend = new ArrayList<Friend>();
				for (int i = 0; i < friendArr.length(); i++) {
					friendJson = friendArr.getJSONObject(i);
					friend = new Friend();
					if (friendJson.opt("user_name") != null) {
						friend.setUser_name(friendJson.getString("user_name"));
					}
					if (friendJson.opt("user_id") != null) {
						friend.setUser_id(friendJson.getString("user_id"));
					}
					lstFriend.add(friend);
				}
				photo.setFriend(lstFriend);

				if (jObject.opt("time_post") != null) {
					photo.setTime_post(jObject.getLong("time_post"));
				}
				if (jObject.opt("caption") != null) {
					photo.setCaption(jObject.getString("caption"));
				}
				if (jObject.opt("is_clicked") != null) {
					photo.setIs_clicked(jObject.getBoolean("is_clicked"));
				}
				if (jObject.opt("is_commented") != null) {
					photo.setIs_commented(jObject.getBoolean("is_commented"));
				}
				if (jObject.opt("url_id") != null) {
					photo.setUrl_id(jObject.getString("url_id"));
				}
			}
		} catch (JSONException e) {
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return photo;
	}

	/**
	 * ID: API8.30 Name: getListPhotoDetail
	 * 
	 * @param json
	 * @return
	 */
	public ArrayList<Photo> getListPhotoDetail(String json) {
		ArrayList<Photo> lstPhoto = new ArrayList<Photo>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray arrPhoto = jObject.getJSONArray("photo");
				Photo photo = null;
				JSONObject photoJson = null;
				for (int i = 0; i < arrPhoto.length(); i++) {
					photoJson = arrPhoto.getJSONObject(i);
					photo = new Photo();

					if (photoJson.opt("photo_id") != null) {
						photo.setPhoto_id(photoJson.getString("photo_id"));
					}
					if (photoJson.opt("button_type") != null) {
						photo.setButton_type(photoJson.getInt("button_type"));
					}
					if (photoJson.opt("number_click") != null) {
						photo.setNumber_click(photoJson.getInt("number_click"));
					}
					if (photoJson.opt("url_photo") != null) {
						photo.setUrl_photo(photoJson.getString("url_photo"));
					}
					if (photoJson.opt("url_thumb") != null) {
						photo.setUrl_thumb(photoJson.getString("url_thumb"));
					}

					// comment
					JSONArray commentArr = photoJson.getJSONArray("comment");
					Comment comment = null;
					JSONObject comJson = null;
					ArrayList<Comment> lstComment = new ArrayList<Comment>();
					for (int j = 0; j < commentArr.length(); j++) {
						comJson = commentArr.getJSONObject(j);
						comment = new Comment();
						if (comJson.opt("comment_id") != null) {
							comment.setComment_id(comJson
									.getString("comment_id"));
						}
						if (comJson.opt("content") != null) {
							comment.setContent(comJson.getString("content"));
						}
						if (comJson.opt("time_post") != null) {
							comment.setTime_post(comJson.getLong("time_post"));
						}
						if (comJson.opt("user_id") != null) {
							comment.setUser_id(comJson.getString("user_id"));
						}
						if (comJson.opt("user_name") != null) {
							comment.setUser_name(comJson.getString("user_name"));
						}
						if (comJson.opt("url_image") != null) {
							comment.setUrl_image(comJson.getString("url_image"));
						}

						lstComment.add(comment);
					}
					photo.setComment(lstComment);
					if (photoJson.opt("number_comment") != null) {
						photo.setNumber_comment(photoJson
								.getInt("number_comment"));
					}
					if (photoJson.opt("list_tags") != null) {
						photo.setList_tags(photoJson.getString("list_tags"));
					}
					if (photoJson.opt("location_text") != null) {
						photo.setLocation_text(photoJson
								.getString("location_text"));
					}
					if (photoJson.opt("location_id") != null) {
						photo.setLocation_id(photoJson.getString("location_id"));
					}
					if (photoJson.opt("longitude") != null) {
						photo.setLongitude(photoJson.getDouble("longitude"));
					}
					if (photoJson.opt("latitude") != null) {
						photo.setLatitude(photoJson.getDouble("latitude"));
					}
					if (photoJson.opt("location_longitude") != null) {
						photo.setLocation_longitude(photoJson
								.getDouble("location_longitude"));
					}
					if (photoJson.opt("location_latitude") != null) {
						photo.setLocation_latitude(photoJson
								.getDouble("location_latitude"));
					}

					// user
					JSONObject userJson = photoJson.getJSONObject("user");
					User user = new User();
					if (userJson.opt("user_name") != null) {
						user.setUser_name(userJson.getString("user_name"));
					}
					if (userJson.opt("user_id") != null) {
						user.setUser_id(userJson.getString("user_id"));
					}
					if (userJson.opt("url_image") != null) {
						user.setUrl_image(userJson.getString("url_image"));
					}
					if (userJson.opt("is_following") != null) {
						user.setIs_following(userJson
								.getBoolean("is_following"));
					}
					photo.setUser(user);

					// friend
					JSONArray friendArr = photoJson.getJSONArray("friend");
					Friend friend = null;
					JSONObject friendJson = null;
					ArrayList<Friend> lstFriend = new ArrayList<Friend>();
					for (int j = 0; j < friendArr.length(); j++) {
						friendJson = friendArr.getJSONObject(j);
						friend = new Friend();
						if (friendJson.opt("user_name") != null) {
							friend.setUser_name(friendJson
									.getString("user_name"));
						}
						if (friendJson.opt("user_id") != null) {
							friend.setUser_id(friendJson.getString("user_id"));
						}
						lstFriend.add(friend);
					}
					photo.setFriend(lstFriend);

					if (photoJson.opt("time_post") != null) {
						photo.setTime_post(photoJson.getLong("time_post"));
					}
					if (photoJson.opt("caption") != null) {
						photo.setCaption(photoJson.getString("caption"));
					}
					if (photoJson.opt("is_clicked") != null) {
						photo.setIs_clicked(photoJson.getBoolean("is_clicked"));
					}
					if (photoJson.opt("is_commented") != null) {
						photo.setIs_commented(photoJson
								.getBoolean("is_commented"));
					}
					if (photoJson.opt("url_id") != null) {
						photo.setUrl_id(photoJson.getString("url_id"));
					}

					lstPhoto.add(photo);
				}
			}
		} catch (JSONException e) {
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstPhoto;
	}

	// TODO: remove
	// /**
	// * ID: API8.3 Name: getUserViewDetail
	// * @param json
	// * @return
	// */
	// public User getUserViewDetail(String json) {
	// User user = new User();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if(jObject.getBoolean("is_success")){
	// user.setUser_id(jObject.getString("user_id"));
	// user.setUser_name(jObject.getString("user_name"));
	// user.setFirst_name(jObject.getString("first_name"));
	// user.setLast_name(jObject.getString("last_name"));
	// user.setIs_following(jObject.getBoolean("is_following"));
	// user.setIs_request(jObject.getBoolean("is_request"));
	// user.setUrl_image(jObject.getString("url_image"));
	// user.setAbout(jObject.getString("about"));
	// user.setNumber_following(jObject.getInt("number_following"));
	// user.setNumber_follower(jObject.getInt("number_follower"));
	// user.setNumber_Vangard(jObject.getInt("number_Vangard"));
	// user.setNumber_Master(jObject.getInt("number_Master"));
	// user.setNumber_Pennant(jObject.getInt("number_Pennant"));
	// user.setNumber_post(jObject.getInt("number_post"));
	// user.setIs_private(jObject.getBoolean("is_private"));
	// user.setIs_blocked(jObject.getBoolean("is_blocked"));
	// user.setFollow_request(jObject.getBoolean("follow_request"));
	// user.setBlock_user(jObject.getBoolean("block_user"));
	// user.setCurrent_city(jObject.getString("current_city"));
	// user.setCurrent_country(jObject.getString("current_country"));
	// } else {
	// //show message when login error
	// user.setMessage(jObject.getString("message"));
	// }
	//
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return user;
	// }

	/**
	 * ID: API8.7 Name: getCommentOfPhoto
	 * 
	 * @param json
	 * @return
	 */
	public ArrayList<Comment> getCommentOfPhoto(String json) {
		ArrayList<Comment> lstComment = new ArrayList<Comment>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				// comment
				JSONArray commentArr = jObject.getJSONArray("comment");
				Comment comment = null;
				JSONObject comJson = null;
				for (int i = 0; i < commentArr.length(); i++) {
					comJson = commentArr.getJSONObject(i);
					comment = new Comment();
					if (comJson.opt("comment_id") != null) {
						comment.setComment_id(comJson.getString("comment_id"));
					}
					if (comJson.opt("content") != null) {
						comment.setContent(comJson.getString("content"));
					}
					if (comJson.opt("time_post") != null) {
						comment.setTime_post(comJson.getLong("time_post"));
					}
					if (comJson.opt("user_id") != null) {
						comment.setUser_id(comJson.getString("user_id"));
					}
					if (comJson.opt("user_name") != null) {
						comment.setUser_name(comJson.getString("user_name"));
					}
					if (comJson.opt("url_image") != null) {
						comment.setUrl_image(comJson.getString("url_image"));
					}
					if (comJson.opt("first_name") != null) {
						comment.setFirst_name(comJson.getString("first_name"));
					}
					if (comJson.opt("last_name") != null) {
						comment.setLast_name(comJson.getString("last_name"));
					}
					lstComment.add(comment);
				}
			}
		} catch (JSONException e) {
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstComment;
	}

	// TODO: remove
	// /**
	// * ID: API8.8 Name: getListPhotoByTags
	// * @param json
	// * @return
	// */
	// public ArrayList<Photo> getListPhotoByTags(String json) {
	// ArrayList<Photo> lstPhoto = new ArrayList<Photo>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if (jObject.getBoolean("is_success")) {
	// JSONArray photoArray = jObject.getJSONArray("photo");
	// Photo photo = null;
	// JSONObject photoJson = null;
	// for (int i = 0; i < photoArray.length(); i++) {
	// photoJson = photoArray.getJSONObject(i);
	// photo = new Photo();
	// if (photoJson.opt("photo_id") != null) {
	// photo.setPhoto_id(photoJson.getString("photo_id"));
	// }
	// if (photoJson.opt("url_thumb") != null) {
	// photo.setUrl_thumb(photoJson.getString("url_thumb"));
	// }
	// if (photoJson.opt("latitude") != null) {
	// photo.setLatitude(photoJson.getDouble("latitude"));
	// }
	// if (photoJson.opt("longitude") != null) {
	// photo.setLongitude(photoJson.getDouble("longitude"));
	// }
	// lstPhoto.add(photo);
	// }
	// }
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return lstPhoto;
	// }

	// TODO: remove
	// /**
	// * ID: API8.10 Name: getListPhotoByYOU
	// * @param json
	// * @return
	// */
	// public ArrayList<Photo> getListPhotoByYOU(String json) {
	// ArrayList<Photo> lstPhoto = new ArrayList<Photo>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if (jObject.getBoolean("is_success")) {
	// JSONArray photoArray = jObject.getJSONArray("photo");
	// Photo photo = null;
	// JSONObject photoJson = null;
	// for (int i = 0; i < photoArray.length(); i++) {
	// photoJson = photoArray.getJSONObject(i);
	// photo = new Photo();
	// photo.setPhoto_id(photoJson.getString("photo_id"));
	// photo.setUrl_thumb(photoJson.getString("url_thumb"));
	// photo.setLatitude(photoJson.getDouble("latitude"));
	// photo.setLongitude(photoJson.getDouble("longitude"));
	// lstPhoto.add(photo);
	// }
	// }
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return lstPhoto;
	// }

	/**
	 * ID: API8.11 Name: getListUserTitleDetail
	 * 
	 * @param json
	 * @return
	 */
	public ArrayList<Tag> getListUserTitleDetail(String json) {
		ArrayList<Tag> lstTag = new ArrayList<Tag>();
		try {
			JSONObject jObject = new JSONObject(json);

			if (jObject.getBoolean("is_success")) {
				JSONArray tagArray = jObject.getJSONArray("tag");
				JSONObject tagJson = null;
				Tag tag = null;
				for (int i = 0; i < tagArray.length(); i++) {
					tagJson = tagArray.getJSONObject(i);
					tag = new Tag();
					if (tagJson.opt("my_post") != null) {
						tag.setMy_post(tagJson.getInt("my_post"));
					}
					if (tagJson.opt("tag_name") != null) {
						tag.setTag_name(tagJson.getString("tag_name"));
					}
					if (tagJson.opt("title") != null) {
						tag.setTitle(tagJson.getString("title"));
					}

					JSONArray arrayPhotoJson = null;
					JSONObject photoJson = null;
					Photo photo = null;
					ArrayList<Photo> lstPhoto = new ArrayList<Photo>();
					// create list photo of tag
					arrayPhotoJson = tagJson.getJSONArray("photo");
					for (int j = 0; j < arrayPhotoJson.length(); j++) {
						photoJson = arrayPhotoJson.getJSONObject(j);
						photo = new Photo();
						if (photoJson.opt("photo_id") != null) {
							photo.setPhoto_id(photoJson.getString("photo_id"));
						}
						if (photoJson.opt("url_thumb") != null) {
							photo.setUrl_thumb(photoJson.getString("url_thumb"));
						}

						lstPhoto.add(photo);
					}
					tag.setPhoto(lstPhoto);

					lstTag.add(tag);
				}

			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstTag;
	}

	// TODO: remove
	// /**
	// * ID: API8.16 Name: checkUserIsBlocked
	// * @param json
	// * @return
	// */
	// public ReportErrors checkUserIsBlocked(String json) {
	// ReportErrors report = new ReportErrors();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// report.setIs_success(jObject.getBoolean("is_success"));
	// report.setMessage(jObject.getString("message"));
	// report.setIs_blocked(jObject.getBoolean("is_blocked"));
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return report;
	// }

	// TODO: Remove
	// /**
	// * ID: API8.20 Name: getListPopularPhoto
	// * @param json
	// * @return
	// */
	// public ArrayList<Photo> getListPopularPhoto(String json) {
	// ArrayList<Photo> lstPhoto = new ArrayList<Photo>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if (jObject.getBoolean("is_success")) {
	// JSONArray photoArray = jObject.getJSONArray("photo");
	// Photo photo = null;
	// JSONObject photoJson = null;
	// for (int i = 0; i < photoArray.length(); i++) {
	// photoJson = photoArray.getJSONObject(i);
	// photo = new Photo();
	// photo.setPhoto_id(photoJson.getString("photo_id"));
	// photo.setUrl_thumb(photoJson.getString("url_thumb"));
	// photo.setLatitude(photoJson.getDouble("latitude"));
	// photo.setLongitude(photoJson.getDouble("longitude"));
	// lstPhoto.add(photo);
	// }
	// }
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return lstPhoto;
	// }

	// TODO: remove
	// /**
	// * ID: API8.25 Name: getPhotoOfUser
	// * @param json
	// * @return
	// */
	// public ArrayList<Photo> getPhotoOfUser(String json) {
	// ArrayList<Photo> lstPhoto = new ArrayList<Photo>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if (jObject.getBoolean("is_success")) {
	// JSONArray photoArray = jObject.getJSONArray("photo");
	// Photo photo = null;
	// JSONObject photoJson = null;
	// for (int i = 0; i < photoArray.length(); i++) {
	// photoJson = photoArray.getJSONObject(i);
	// photo = new Photo();
	// photo.setPhoto_id(photoJson.getString("photo_id"));
	// photo.setUrl_thumb(photoJson.getString("url_thumb"));
	// photo.setLatitude(photoJson.getDouble("latitude"));
	// photo.setLongitude(photoJson.getDouble("longitude"));
	// lstPhoto.add(photo);
	// }
	// }
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return lstPhoto;
	// }

	/**
	 * ID: API8.26 Name: getPhotoLinkToOtherServices
	 * 
	 * @param json
	 * @return
	 */
	public PhotoLink getPhotoLinkToOtherServices(String json) {
		PhotoLink link = new PhotoLink();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				if (jObject.opt("link_facebook") != null) {
					link.setLink_facebook(jObject.getInt("link_facebook"));
				}
				if (jObject.opt("link_weibo") != null) {
					link.setLink_weibo(jObject.getInt("link_weibo"));
				}
				if (jObject.opt("link_twitter") != null) {
					link.setLink_twitter(jObject.getInt("link_twitter"));
				}
				if (jObject.opt("link_tumblr") != null) {
					link.setLink_tumblr(jObject.getInt("link_tumblr"));
				}
				if (jObject.opt("link_flickr") != null) {
					link.setLink_flickr(jObject.getInt("link_flickr"));
				}
			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return link;
	}

	// TODO: remove
	// /**
	// * ID: API8.27 Name: getListPhotoByLocationID
	// * @param json
	// * @return
	// */
	// public ArrayList<Photo> getListPhotoByLocationID(String json) {
	// ArrayList<Photo> lstPhoto = new ArrayList<Photo>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if (jObject.getBoolean("is_success")) {
	// JSONArray photoArray = jObject.getJSONArray("photo");
	// Photo photo = null;
	// JSONObject photoJson = null;
	// for (int i = 0; i < photoArray.length(); i++) {
	// photoJson = photoArray.getJSONObject(i);
	// photo = new Photo();
	// photo.setPhoto_id(photoJson.getString("photo_id"));
	// photo.setUrl_thumb(photoJson.getString("url_thumb"));
	// photo.setLatitude(photoJson.getDouble("latitude"));
	// photo.setLongitude(photoJson.getDouble("longitude"));
	// lstPhoto.add(photo);
	// }
	// }
	// } catch (JSONException e) {
	// //TODO: nothing
	// }
	// return lstPhoto;
	// }

	/**
	 * ID: API8.28 Name: getPhotoDetailOfUser => deleted
	 */

	/**
	 * ID: API4.4 Name: getFavouristTags ID: API4.3 Name: getListTagsRecommend
	 * ID: API4.5 Name: getFrequentTags
	 * 
	 * @param json
	 * @return ArrayList<Tag>
	 */
	public ArrayList<Tag> getTags(String json) {
		ArrayList<Tag> lstTag = new ArrayList<Tag>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray tagArray = jObject.getJSONArray("tag");
				JSONObject tagJson = null;
				Tag tag = null;
				for (int i = 0; i < tagArray.length(); i++) {
					tagJson = tagArray.getJSONObject(i);
					tag = new Tag();
					if (tagJson.opt("my_post") != null) {
						tag.setMy_post(tagJson.getInt("my_post"));
					}
					if (tagJson.opt("number_post") != null) {
						tag.setNumber_post(tagJson.getInt("number_post"));
					}
					if (tagJson.opt("tag_name") != null) {
						tag.setTag_name(tagJson.getString("tag_name"));
					}
					if (tagJson.opt("is_offical") != null) {
						tag.setIs_offical(tagJson.getBoolean("is_offical"));
					}
					if (tagJson.opt("content") != null) {
						tag.setContent(tagJson.getString("content"));
					}
					lstTag.add(tag);
				}
			}
		} catch (JSONException e) {
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstTag;
	}

	// TODO:remove
	// /**
	// * ID: API4.4 Name: getFavouristTags
	// * @param json
	// * @return ArrayList<Tag>
	// */
	// public ArrayList<Tag> getFavouristTags(String json) {
	// ArrayList<Tag> lstTag = new ArrayList<Tag>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if(jObject.getBoolean("is_success")){
	// JSONArray tagArray = jObject.getJSONArray("tag");
	// JSONObject tagJson = null;
	// Tag tag = null;
	// for (int i = 0; i < tagArray.length(); i++) {
	// tagJson = tagArray.getJSONObject(i);
	// tag = new Tag();
	// if (tagJson.opt("my_post") != null) {
	// tag.setMy_post(tagJson.getInt("my_post"));
	// }
	// if (tagJson.opt("number_post") != null) {
	// tag.setNumber_post(tagJson.getInt("number_post"));
	// }
	// if (tagJson.opt("tag_name") != null) {
	// tag.setTag_name(tagJson.getString("tag_name"));
	// }
	// if (tagJson.opt("is_offical") != null) {
	// tag.setIs_offical(tagJson.getBoolean("is_offical"));
	// }
	// lstTag.add(tag);
	// }
	// }
	// } catch (JSONException e) {
	//
	// }
	// return lstTag;
	// }

	// TODO: remove
	// /**
	// * ID: API4.3 Name: getListTagsRecommend
	// * @param json
	// * @return ArrayList<Tag>
	// */
	// public ArrayList<Tag> getListTagsRecommend(String json) {
	// ArrayList<Tag> lstTag = new ArrayList<Tag>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if(jObject.getBoolean("is_success")){
	// JSONArray tagArray = jObject.getJSONArray("tag");
	// JSONObject tagJson = null;
	// Tag tag = null;
	// for (int i = 0; i < tagArray.length(); i++) {
	// tagJson = tagArray.getJSONObject(i);
	// tag = new Tag();
	// tag.setMy_post(tagJson.getInt("my_post"));
	// tag.setNumber_post(tagJson.getInt("number_post"));
	// tag.setContent(tagJson.getString("content"));
	// lstTag.add(tag);
	// }
	// }
	// } catch (JSONException e) {
	//
	// }
	// return lstTag;
	// }

	/**
	 * ID: API4.1 Name: getListFilterLocked
	 * 
	 * @param json
	 * @return ArrayList<FilterLock>
	 */
	public ArrayList<FilterLock> getListFilterLocked(String json) {
		ArrayList<FilterLock> lstFilterLock = new ArrayList<FilterLock>();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				JSONArray filterArray = jObject.getJSONArray("filter_lock");
				JSONObject filterJson = null;
				FilterLock filterLock = null;
				for (int i = 0; i < filterArray.length(); i++) {
					filterJson = filterArray.getJSONObject(i);
					filterLock = new FilterLock();
					if (filterJson.opt("filter_id") != null) {
						filterLock.setFilter_id(filterJson.getInt("filter_id"));
					}
					if (filterJson.opt("pennant_name") != null) {
						filterLock.setPennant_name(filterJson
								.getString("pennant_name"));
					}
					lstFilterLock.add(filterLock);
				}
			}
		} catch (JSONException e) {
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return lstFilterLock;
	}

	/**
	 * ID: API4.2 Name: postPhotoc
	 * 
	 * @param json
	 * @return
	 */
	public Photo postPhoto(String json) {
		Photo photo = new Photo();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				if (jObject.opt("photo_id") != null) {
					photo.setPhoto_id(jObject.getString("photo_id"));
				}
				if (jObject.opt("photo_url") != null) {
					photo.setUrl_photo(jObject.getString("photo_url"));
				}
				if (jObject.opt("url_id") != null) {
					photo.setUrl_id(jObject.getString("url_id"));
				}

				// add tag news
				JSONArray tagArray = jObject.getJSONArray("news");
				JSONObject tagJson = null;
				TagNews news = null;
				ArrayList<TagNews> lstNews = new ArrayList<TagNews>();
				for (int i = 0; i < tagArray.length(); i++) {
					tagJson = tagArray.getJSONObject(i);
					news = new TagNews();
					if (tagJson.opt("tag") != null) {
						news.setTag(tagJson.getString("tag"));
					}
					if (tagJson.opt("message") != null) {
						news.setMessage(tagJson.getString("message"));
					}
					if (tagJson.opt("type") != null) {
						news.setType(tagJson.getInt("type"));
					}
					if (tagJson.opt("score") != null) {
						news.setScore(tagJson.getInt("score"));
					}
					if (tagJson.opt("highscore") != null) {
						news.setHighscore(tagJson.getInt("highscore"));
					}
					lstNews.add(news);
				}
				photo.setNews(lstNews);
			}
		} catch (JSONException e) {
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return photo;
	}

	// TODO: remove
	// /**
	// * ID: API4.5 Name: getFrequentTags
	// * @param json
	// * @return ArrayList<Tag>
	// */
	// public ArrayList<Tag> getFrequentTags(String json) {
	// ArrayList<Tag> lstTag = new ArrayList<Tag>();
	// try {
	// JSONObject jObject = new JSONObject(json);
	// if(jObject.getBoolean("is_success")){
	// JSONArray tagArray = jObject.getJSONArray("tag");
	// JSONObject tagJson = null;
	// Tag tag = null;
	// for (int i = 0; i < tagArray.length(); i++) {
	// tagJson = tagArray.getJSONObject(i);
	// tag = new Tag();
	// tag.setMy_post(tagJson.getInt("my_post"));
	// tag.setNumber_post(tagJson.getInt("number_post"));
	// tag.setTag_name(tagJson.getString("tag_name"));
	// tag.setIs_offical(tagJson.getBoolean("is_offical"));
	// lstTag.add(tag);
	// }
	// }
	// } catch (JSONException e) {
	//
	// }
	// return lstTag;
	// }

	/**
	 * ID: API7.13 Name: getUserStatus
	 * 
	 * @param json
	 * @return
	 */
	public UserStatus getUserStatus(String json) {
		UserStatus status = new UserStatus();
		try {
			JSONObject jObject = new JSONObject(json);
			if (jObject.getBoolean("is_success")) {
				jObject = jObject.getJSONObject("status");

				if (jObject.opt("message") != null) {
					status.setMessage(jObject.getString("message"));
				}
				if (jObject.opt("number_follower") != null) {
					status.setNumber_follower(jObject.getInt("number_follower"));
				}
				if (jObject.opt("number_following") != null) {
					status.setNumber_following(jObject
							.getInt("number_following"));
				}
				if (jObject.opt("number_follow_both") != null) {
					status.setNumber_follow_both(jObject
							.getInt("number_follow_both"));
				}
				if (jObject.opt("number_photos") != null) {
					status.setNumber_photos(jObject.getInt("number_photos"));
				}
				if (jObject.opt("number_comment") != null) {
					status.setNumber_comment(jObject.getInt("number_comment"));
				}
				if (jObject.opt("number_comment_per_day") != null) {
					status.setNumber_comment_per_day(jObject
							.getDouble("number_comment_per_day"));
				}
				if (jObject.opt("number_rating_weeks") != null) {
					status.setNumber_rating_weeks(jObject
							.getInt("number_rating_weeks"));
				}
				if (jObject.opt("number_rating_weeks_per_day") != null) {
					status.setNumber_rating_weeks_per_day(jObject
							.getDouble("number_rating_weeks_per_day"));
				}
				if (jObject.opt("number_rating") != null) {
					status.setNumber_rating(jObject.getInt("number_rating"));
				}
				if (jObject.opt("number_rating_per_day") != null) {
					status.setNumber_rating_per_day(jObject
							.getDouble("number_rating_per_day"));
				}
				if (jObject.opt("number_photo_no_filter") != null) {
					status.setNumber_photo_no_filter(jObject
							.getInt("number_photo_no_filter"));
				}

				// rating total
				JSONArray arr = jObject.getJSONArray("rating_total");
				JSONObject jOj = null;
				RatingTotal ratingTotal = null;
				ArrayList<RatingTotal> lstRt = new ArrayList<RatingTotal>();
				for (int i = 0; i < arr.length(); i++) {
					jOj = arr.getJSONObject(i);
					ratingTotal = new RatingTotal();
					if (jOj.opt("button_type") != null) {
						ratingTotal.setButton_type(jOj.getInt("button_type"));
					}
					if (jOj.opt("number_post") != null) {
						ratingTotal.setNumber_post(jOj.getInt("number_post"));
					}
					lstRt.add(ratingTotal);
				}
				status.setRating_total(lstRt);

				// rating_weeks
				arr = jObject.getJSONArray("rating_weeks");
				RatingWeeks ratingWeeks = null;
				ArrayList<RatingWeeks> lstRw = new ArrayList<RatingWeeks>();
				for (int i = 0; i < arr.length(); i++) {
					jOj = arr.getJSONObject(i);
					ratingWeeks = new RatingWeeks();
					if (jOj.opt("button_type") != null) {
						ratingWeeks.setButton_type(jOj.getInt("button_type"));
					}
					if (jOj.opt("number_post") != null) {
						ratingWeeks.setNumber_post(jOj.getInt("number_post"));
					}

					lstRw.add(ratingWeeks);
				}
				status.setRating_weeks(lstRw);

				// favourist_tags
				arr = jObject.getJSONArray("favourist_tags");
				TagInfo favoristTag = null;
				ArrayList<TagInfo> lstFavTag = new ArrayList<TagInfo>();
				for (int i = 0; i < arr.length(); i++) {
					jOj = arr.getJSONObject(i);
					favoristTag = new TagInfo();
					if (jOj.opt("my_post") != null) {
						favoristTag.setMy_post(jOj.getInt("my_post"));
					}
					if (jOj.opt("number_post") != null) {
						favoristTag.setNumber_post(jOj.getInt("number_post"));
					}
					if (jOj.opt("tag_name") != null) {
						favoristTag.setTag_name(jOj.getString("tag_name"));
					}

					lstFavTag.add(favoristTag);
				}
				status.setFavourist_tags(lstFavTag);

				// frequent_tags
				arr = jObject.getJSONArray("frequent_tags");
				TagInfo freTag = null;
				ArrayList<TagInfo> lstFreTag = new ArrayList<TagInfo>();
				for (int i = 0; i < arr.length(); i++) {
					jOj = arr.getJSONObject(i);
					freTag = new TagInfo();
					if (jOj.opt("my_post") != null) {
						freTag.setMy_post(jOj.getInt("my_post"));
					}
					if (jOj.opt("number_post") != null) {
						freTag.setNumber_post(jOj.getInt("number_post"));
					}
					if (jOj.opt("tag_name") != null) {
						freTag.setTag_name(jOj.getString("tag_name"));
					}
					lstFreTag.add(freTag);
				}
				status.setFrequent_tags(lstFreTag);

				// top_photos
				arr = jObject.getJSONArray("top_photos");
				TopPhotos topPhoto = null;
				ArrayList<TopPhotos> lstTopPhoto = new ArrayList<TopPhotos>();
				for (int i = 0; i < arr.length(); i++) {
					jOj = arr.getJSONObject(i);
					topPhoto = new TopPhotos();
					if (jOj.opt("photo_id") != null) {
						topPhoto.setPhoto_id(jOj.getString("photo_id"));
					}
					if (jOj.opt("url_thumb") != null) {
						topPhoto.setUrl_thumb(jOj.getString("url_thumb"));
					}
					if (jOj.opt("button_type") != null) {
						topPhoto.setButton_type(jOj.getString("button_type"));
					}
					if (jOj.opt("number_clicked") != null) {
						topPhoto.setNumber_clicked(jOj.getInt("number_clicked"));
					}
					lstTopPhoto.add(topPhoto);
				}
				status.setTop_photos(lstTopPhoto);

				// filter
				arr = jObject.getJSONArray("filters");
				Filter filter = null;
				ArrayList<Filter> lstFilter = new ArrayList<Filter>();
				for (int i = 0; i < arr.length(); i++) {
					jOj = arr.getJSONObject(i);
					filter = new Filter();
					if (jOj.opt("filter_id") != null) {
						filter.setFilter_id(jOj.getInt("filter_id"));
					}
					if (jOj.opt("number_use") != null) {
						filter.setNumber_use(jOj.getInt("number_use"));
					}
					lstFilter.add(filter);
				}
				status.setFilter(lstFilter);

				// posts
				arr = jObject.getJSONArray("posts");
				PostsOfWeek post = null;
				ArrayList<PostsOfWeek> lstPost = new ArrayList<PostsOfWeek>();
				for (int i = 0; i < arr.length(); i++) {
					jOj = arr.getJSONObject(i);
					post = new PostsOfWeek();
					if (jOj.opt("dayOfWeek") != null) {
						post.setDayOfWeek(jOj.getInt("dayOfWeek"));
					}
					if (jOj.opt("number_post") != null) {
						post.setNumber_post(jOj.getInt("number_post"));
					}

					lstPost.add(post);
				}
				status.setPosts(lstPost);

			}
		} catch (JSONException e) {
			// TODO: nothing
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return status;
	}

	// CUONGDC

	public User getUserProfileFacebook(String json) {
		User user = new User();
		Log.d("json", json);
		try {

			JSONObject jObject = new JSONObject(json);

			if (jObject.opt("id") != null) {
				user.setFacebook_id(jObject.getString("id"));
				String url = "https://graph.facebook.com/"
						+ user.getFacebook_id() + "/picture?type=large";
				user.setUrl_image(url);
			}
			if (jObject.opt("username") != null) {
				user.setUser_name(jObject.getString("username"));
			}

			if (jObject.opt("first_name") != null) {
				user.setFirst_name(jObject.getString("first_name"));
			}
			if (jObject.opt("last_name") != null) {
				user.setLast_name(jObject.getString("last_name"));
			}
			if (jObject.opt("email") != null) {
				user.setEmail(jObject.getString("email"));
			}

			if (jObject.opt("birthday") != null) {
				user.setBirthday(jObject.getString("birthday"));
			}
			if (jObject.opt("gender") != null) {
				String male = jObject.getString("gender");
				if (male.equals("male")) {
					user.setMale("1");
				} else {
					user.setMale("0");
				}
			}
			if (jObject.opt("interested_in") != null) {
				JSONArray inter = jObject.getJSONArray("interested_in");
				if (inter.length() == 2) {
					user.setInterested("A");
				}
				if (inter.length() == 1) {
					if (inter.getString(0).equals("female")) {
						user.setInterested("F");
						Log.d("female", user.getInterested());
					} else {
						user.setInterested("M");
						Log.d("male", user.getInterested());
					}
				}
			}
			if (jObject.opt("location") != null) {
				JSONObject location = jObject.getJSONObject("location");
				if (location.opt("name") != null) {
					user.setCurrent_city(location.getString("name"));
				}
				if (location.opt("id") != null) {
					user.setLocation_idFB(location.getString("id"));
				}
			}
		} catch (Exception e) {
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return user;

	}

	// END CUONGDC

	/**
	 * LINHLM: lay thong tin Place xung quanh
	 * 
	 * @param json
	 * @return
	 */
	public ArrayList<Place> getPlace(String json) {
		ArrayList<Place> listPlace = new ArrayList<Place>();
		try {
			JSONArray jsonArray = new JSONObject(json).getJSONArray("data");
			if (jsonArray != null) {
				for (int i = 0; i < jsonArray.length(); i++) {
					Place place = new Place();
					if (jsonArray.getJSONObject(i).getString("name") != null) {
						place.setName(jsonArray.getJSONObject(i).getString(
								"name"));
					}
					if (jsonArray.getJSONObject(i).getString("id") != null) {
						place.setIdPlace(jsonArray.getJSONObject(i).getString(
								"id"));
					}
					if (jsonArray.getJSONObject(i).getString("category") != null) {
						place.setCategory(jsonArray.getJSONObject(i).getString(
								"category"));
					}

					if (jsonArray.getJSONObject(i).opt("location") != null) {
						if (jsonArray.getJSONObject(i)
								.getJSONObject("location").opt("city") != null) {
							place.setCity(jsonArray.getJSONObject(i)
									.getJSONObject("location")
									.getString("city"));
						}
						if (jsonArray.getJSONObject(i)
								.getJSONObject("location").opt("country") != null) {
							place.setCountry(jsonArray.getJSONObject(i)
									.getJSONObject("location")
									.getString("country"));
						}
						if (jsonArray.getJSONObject(i)
								.getJSONObject("location")
								.optDouble("latitude") != 0) {
							place.setLatitude(jsonArray.getJSONObject(i)
									.getJSONObject("location")
									.getDouble("latitude"));
						}
						if (jsonArray.getJSONObject(i)
								.getJSONObject("location")
								.optDouble("longitude") != 0) {
							place.setLongitude(jsonArray.getJSONObject(i)
									.getJSONObject("location")
									.getDouble("longitude"));
						}
					}

					listPlace.add(place);
				}
			} else {
				listPlace = null;
			}
		} catch (JSONException e) {
			// Toast.makeText(mContext, mContext.getString(R.string.ERR0007),
			// Toast.LENGTH_LONG).show();
		}
		return listPlace;
	}

}
